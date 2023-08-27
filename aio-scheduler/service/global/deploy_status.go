package global

import (
	"aio-scheduler/common/constants"
	"sync"
)

var deployStatus = constants.Init
var rwMutex = sync.RWMutex{}

func GetDeployStatus() string {
	rwMutex.RLock()
	defer rwMutex.RUnlock()
	return deployStatus
}

func CasDeployStatus(oldStatus string, newStatus string) bool {
	rwMutex.Lock()
	defer rwMutex.Unlock()
	if deployStatus == oldStatus {
		deployStatus = newStatus
		return true
	} else {
		return false
	}
}

func IsDeployed() bool {
	return GetDeployStatus() == constants.Deployed
}
