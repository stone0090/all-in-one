package service

import (
	"aio-scheduler/common/constants"
	"aio-scheduler/common/request"
	"github.com/labstack/gommon/log"
	"github.com/robfig/cron/v3"
	"strconv"
	"time"
)

func newScheduleTask(scheduleInfo request.ScheduleInfo, callback func()) {
	log.Infof("创建定时任务，ScheduleInfo为: %v", scheduleInfo)
	var err error
	if scheduleInfo.Type == constants.ScheduleTypeCron {
		err = newScheduleTaskByCron(scheduleInfo.Expression, callback)
	}
	if scheduleInfo.Type == constants.ScheduleTypeInterval {
		err = newScheduleTaskByPeriod(scheduleInfo.Expression, callback)
	}
	if err != nil {
		log.Errorf("创建定时任务失败，ScheduleInfo为: %v，错误信息为: %v", scheduleInfo, err.Error())
	}
}

func newScheduleTaskByCron(expression string, callback func()) error {
	cronTask := newWithSecond()
	_, err := cronTask.AddFunc(expression, func() {
		log.Info("执行定时任务...")
		callback()
	})
	if err != nil {
		return err
	}
	cronTask.Start()
	log.Infof("cron定时任务已启动，表达式为: %v", expression)
	return nil
}

func newWithSecond() *cron.Cron {
	secondParser := cron.NewParser(cron.Second | cron.Minute | cron.Hour | cron.Dom | cron.Month | cron.DowOptional | cron.Descriptor)
	return cron.New(cron.WithParser(secondParser), cron.WithChain(cron.Recover(cron.DefaultLogger)))
}

func newScheduleTaskByPeriod(expression string, callback func()) error {
	//expression to millisecond
	millisecond, err := strconv.Atoi(expression)
	if err != nil {
		return err
	}
	taskChan := make(chan bool, 1)
	start := time.Now().UnixMilli()
	go notice(start, millisecond, &taskChan)
	go execute(callback, &taskChan)
	return nil
}

func notice(start int64, millisecond int, taskChan *chan bool) {
	var count = 1
	for {
		now := time.Now().UnixMilli()
		next := start + int64(count*millisecond)
		count++
		if next < now {
			log.Errorf("定时任务执行时间已过期，当前时间为[%v]，下次执行时间为[%v]", now, next)
			continue
		}
		wait := next - now
		time.Sleep(time.Duration(wait) * time.Millisecond)
		*taskChan <- true
	}
}

func execute(callback func(), taskChan *chan bool) {
	for {
		<-*taskChan
		log.Info("执行定时任务...")
		callback()
	}
}
