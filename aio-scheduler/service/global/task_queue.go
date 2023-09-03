package global

import (
	"github.com/hashicorp/golang-lru/v2"
	"github.com/labstack/gommon/log"
	"sync"
)

var taskQueue TaskQueue
var rwLock sync.RWMutex

type TaskQueue struct {
	size     int
	lruCache *lru.Cache[string, int64]
}

func NewTaskQueue(size int) TaskQueue {
	taskQueue = TaskQueue{}
	taskQueue.size = size
	taskQueue.lruCache, _ = lru.New[string, int64](size)
	return taskQueue
}

func (tq *TaskQueue) AddTaskAndCallback(key string, val int64, callback func()) {
	defer rwLock.Unlock()
	rwLock.Lock()
	log.Infof("添加LRU队列，key为[%v]，val为[%v]", key, val)
	switch tq.lruCache.Len() {
	case 0:
		tq.lruCache.Add(key, val)
		go callback()
	case tq.size:
		tq.lruCache.Purge()
		tq.lruCache.Add(key, val)
		go callback()
	default:
		tq.lruCache.Add(key, val)
	}
}

func (tq *TaskQueue) CallbackIfExist(key string, callback func()) {
	defer rwLock.RUnlock()
	rwLock.RLock()
	log.Infof("检查LRU队列，key为[%v]", key)
	if tq.lruCache.Contains(key) {
		log.Infof("LRU队列中存在key为[%v]的值", key)
		go callback()
	} else {
		log.Infof("LRU队列中不存在key为[%v]的值", key)
	}
}

func (tq *TaskQueue) RemoveAllTask(key string) {
	defer rwLock.Unlock()
	rwLock.Lock()
	log.Infof("当存在key[%v]时，清空LRU队列...", key)
	if tq.lruCache.Contains(key) {
		tq.lruCache.Purge()
		log.Info("清空LRU队列成功！")
	}
}
