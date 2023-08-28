import json

with open('config.json', 'r') as config_file:
    config = json.load(config_file)

with open('inject_code.py', 'w') as inject_code_file:
    inject_code_file.write(config['algo_code'])

print(config['service_port'])
