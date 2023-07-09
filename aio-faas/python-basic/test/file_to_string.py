def file_to_escaped_string(file_path):
    with open(file_path, 'r') as file:
        content = file.read()
        escaped_string = content.encode('unicode_escape').decode()
    return escaped_string


file_path = '../inject_code2.py'
file_content = file_to_escaped_string(file_path)
print(file_content)