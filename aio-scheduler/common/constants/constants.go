package constants

type RunMode string
type Language string
type ScheduleType string
type DeployStatus string
type PortType string

const Mode = "mode"
const Dev = "dev"
const Prod = "prod"

const Init = "init"
const Running = "running"

const Deploying = "deploying"
const Deployed = "deployed"

const Success = "success"
const Failure = "failure"

const Python = "python"

const ScheduleTypeInterval = "interval"
const ScheduleTypeCron = "cron"
const ScheduleTypeNone = "none"

const PortTypeOutput = "output"
const PortTypeInput = "input"
