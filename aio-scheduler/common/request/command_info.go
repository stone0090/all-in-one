package request

type CommandInfo struct {
	Name string   `json:"name"`
	Args []string `json:"args"`
}
