MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('1', 'stone', '123', '小方方'); -- REPLACE INTO
MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('2', 'sophie', '123', '小平平'); -- REPLACE INTO
MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('3', 'tommy', '123', '小黑黑'); -- REPLACE INTO
MERGE INTO `aio_role`(id, role_code, role_name) VALUES ('1', 'admin', '管理员'); -- REPLACE INTO
MERGE INTO `aio_role`(id, role_code, role_name) VALUES ('2', 'visitor', '游客'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('1', 'insert_user', '新增用户', '/user/add'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('2', 'delete_user', '删除用户', '/user/remove'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('3', 'update_user', '更新用户', '/user/edit'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('4', 'select_one_user', '查询单个用户', '/user/get'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('5', 'select_all_user', '查询全部用户', '/user/list'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('1', 'stone', 'admin'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('2', 'sophie', 'admin'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('3', 'tommy', 'visitor'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('1', 'admin', 'insert_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('2', 'admin', 'delete_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('3', 'admin', 'update_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('4', 'admin', 'select_one_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('5', 'admin', 'select_all_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('6', 'visitor', 'select_all_user'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('1', 'operator_algorithm_code_template', '# import start

# customer code start
def main(input_data):
    a = input_data["a"]
    b = input_data["b"]
    return {
        "product": a + b
    }
# customer code end
'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('2', 'operator_input_param_template', '{
    "a": {
        "name": "参数a",
        "type": "int/double/string/boolean",
        "required": true
    },
    "b": {
        "name": "参数b",
        "type": "int/double/string/boolean",
        "required": true
    },
}'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('3', 'operator_output_param_template', '{
    "sum": {
        "name": "两数之和sum",
        "type": "int/double/string/boolean"
    }
}'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('4', 'default_k8s_kube_config', 'apiVersion: v1
clusters:
- cluster:
    certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJek1EWXlOREUwTWpZeU1Gb1hEVE16TURZeU1URTBNall5TUZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBS0tEClNUZi83T24vakx0SjRTWHlPNkFwVXBVLzVBZUdhQU9oZk9lbTE1di93K1d2dUlwWVpxM1dHdTk4RDZDN2dwL2MKTEFUbkEvMFpoZ0tMclJLRTZSUjBkTzVQamNaTWFnZUdmQmhGMS9BQXduYkZvVVFQUklTT1M1a0tvZlFZMHdwUQpQc3dyY0x2TENkWC9uVGwwUEV3L0pIczV5MUtEdmVIbmE4bnhFYml2cDluYmdKRTU1ZHZST2ppZHdpdlVKVnRSCi9xNU9ZaTJzNUxjanN4RTRGRGlVRUYvNmZUUU0yNlIra2tjN25YUTZRb2RPT3RHNTE0RXlqQnM2bkZ4RjBzOWEKZ2xsZDVUQUt5c1kySitPYVMzanduM3IrNTBSWEJQVXV0amlUbDhWS3dDamlQSHdxbXFXQnRqNFNqSXNFYXBBVwpLd0Z4My8xK2JuaDdkazJ6KzhjQ0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZNYisyVFJLb2tqc0JUdENBdTQ1bjRCWTVTRi9NQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBQkFFMVpGMzNKb2IzTlpkM3BHOAp3TEZaZ2MycWE3NHNSVGZ0RzFiOWo3WnE4c01Rd2VuclpzellueTFOaGRuVnZrMEM0dmh6Q0x2UjBWYlVxSkt1Clo4QUFmckt0dTBjL2E5cUFYRmlIT3hFNGZSYllhVU9ZVDlqOHBmeVZHekR5R1FwVEFTQmdYeHYvOURQUGxVbFoKWmVXWVBRZGxOVlc0SWlKOEZWb2ZzT1dzOEhScnNvU0puT1N3NWozalZTMUM0MWVveldseHFHRkJScjZ0OEdxVgpaa1cvZDRDTGNVQ3VqMTBuOGZQWUpBUG5Tdlg3K0lDRUhPL2J4dkx4YVZYRFF6VHhReU81bUpnVWxCT2dFcjkvCmtLU2xuVjE4OEFQY05aMnVNNjJTZlNIc2crN1RxV1ZOdUR3UHZnVERjSmgxREI0aU90eUNUZmozV2c3ODhicmkKaWpnPQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
    server: https://kubernetes.docker.internal:6443
  name: docker-desktop
contexts:
- context:
    cluster: docker-desktop
    user: docker-desktop
  name: docker-desktop
current-context: docker-desktop
kind: Config
preferences: {}
users:
- name: docker-desktop
  user:
    client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURRakNDQWlxZ0F3SUJBZ0lJV3hnWUNEcjByNFV3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TXpBMk1qUXhOREkyTWpCYUZ3MHlOREEyTWpNeE5UTTFNemhhTURZeApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sc3dHUVlEVlFRREV4SmtiMk5yWlhJdFptOXlMV1JsCmMydDBiM0F3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3p5S0w2TzV3cnZUczMKdjdLSmpzSXQybDV3YWdKSHpWandmbEg4enpUQXhSaE40OEUrVGw5ZjAvbjR3ZHdmUkVKVy9HVFZSQmQrelBtWAo0Q3B3V1kraTBWZ1VSNGpOME93ZzM4VCtGcmd4RklsTmVlMG1BQk5xZWV1MjRUL3pRYU1nVHM0Q1EvWERFT3BsCmMrT0ZRSHMwMW5IVGNJckdqUUs2T3NiMU9DbnBYMkk5THBYT1ZwTkxsKytxVllHVlEyQlhlSEFyRDNlWUppOWYKbUhQUHprWkRuM3RMOGUrUG5nemRvVzFqN1VWanpBTnE5MTkwUXlRallUMGprMEhUSUwreE9jVGQyRHNVTVdmQQpSWnlFRHZWR0ZlYnM2MTJiZnZDN2lpSHQ2dzE2c1drOTVSN25LeVFaOVcrV0N0SzVEYXBZV08vbGgrTm5WNy85Cm16THdrOC8vQWdNQkFBR2pkVEJ6TUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQU1CZ05WSFJNQkFmOEVBakFBTUI4R0ExVWRJd1FZTUJhQUZNYisyVFJLb2tqc0JUdENBdTQ1bjRCWQo1U0YvTUIwR0ExVWRFUVFXTUJTQ0VtUnZZMnRsY2kxbWIzSXRaR1Z6YTNSdmNEQU5CZ2txaGtpRzl3MEJBUXNGCkFBT0NBUUVBaHV6SmhKcVNldzlnUmYyWkUwYnBKS2dZVHlNd2JGYzBXalZSM1ZCRUZaRkxuS2ZLZEQ2b2o2Y3QKWVM0eURENTdlNjhQUlJHcGZnZUxDanJpd1NOd1FiSmlBUHl5eEpvN1JTNjhXRmFxTTR2NkRkTmVTdkhuVnp1TQpGLzc0bExEOUlrOW1nUjN3alFLVS9ydWwyZENRWmhGcy93bnhEUElWMWxvS0E1ZjNJU2ZGY1ZIb3owT3ZUQ3lPCnBNKzEzNlB0MWZPcEJhZGE1VVBzUWJXSEZMY0ZFQ245R3FCbzNSRDh6bVc4ZEFiYlZiMkR1cUNvT05UU0JtdkwKdzZkYXpHd0t1SnNJbE5vMXluZHRtMmNCbUNuK0J1YUIraEhEeHByVUE3T01wRzY1c2tTek5BakhzeGtXcHJ6cApxZjV3VllHKzJ0UXJaRi9UNm4yZ0hnd0hPUUhLK3c9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
    client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBczhpaStqdWNLNzA3TjcreWlZN0NMZHBlY0dvQ1I4MVk4SDVSL004MHdNVVlUZVBCClBrNWZYOVA1K01IY0gwUkNWdnhrMVVRWGZzejVsK0FxY0ZtUG90RllGRWVJemREc0lOL0UvaGE0TVJTSlRYbnQKSmdBVGFubnJ0dUUvODBHaklFN09Ba1Axd3hEcVpYUGpoVUI3Tk5aeDAzQ0t4bzBDdWpyRzlUZ3A2VjlpUFM2Vgp6bGFUUzVmdnFsV0JsVU5nVjNod0t3OTNtQ1l2WDVoeno4NUdRNTk3Uy9Idmo1NE0zYUZ0WSsxRlk4d0RhdmRmCmRFTWtJMkU5STVOQjB5Qy9zVG5FM2RnN0ZERm53RVdjaEE3MVJoWG03T3RkbTM3d3U0b2g3ZXNOZXJGcFBlVWUKNXlza0dmVnZsZ3JTdVEycVdGanY1WWZqWjFlLy9ac3k4SlBQL3dJREFRQUJBb0lCQUhZeXI2dlNxdzg2bndjOAoraFZrRFJmMlhHYU4yNmYzUXNVQ1Y4YmRON0tSSWpSN1M1NDZDb3hISWk1QXpnYWthdlVqaE91TmsrRGl4Q25zCjBCb0RWK0xVdlpLL29ScW5nOGJ0TjJkUjZwRWlEazhvblJpQnJrSXZuQ0FsSVZVOXp4Qzduc2R3aE5CVmU3VnQKcHFVQnlyN25TZmlZSWpUOW1sQnp1a0NZUnozTlJBaFN6aFd0a1d1TUFkc3dzWHdFdVo1aFZsNnpvSzZKbmVjQwpHdFF4anVFR1JIQkdZQ0E2RFdVTTdRNVVibk1XRGd1aEVkSFBRNm95bGpGaGoxU1RNQkFaU2M3YnNvNFIyZGNZCldsL3J6L3RKZ21PelBoVnFSYVU3b251YmtiY1BYNzdaN1ZqZWdTTk01OWxmQXI3WU9nYXFPb21nNW44dEtPcmQKb0dIdVRJRUNnWUVBNlNhT0ZKekVwSlpxZUVjdEtxNmJ3dHVpTXhMR1NEL3oxajMxakJmTjJBc01VdjJ5QStaNQpka3d6MUl4RUQvdFpaeDdYOTcyUmhxYTlvbjlrOGxPSnM3Rldhd21ISVl5VWdpU0dVNGVkdkRncDRrVnNEK0JjCmxiUkZQTk55L3h4aSszWGNCWG52RC9JSUMzcUZ6YUM4RCtmQk41SVVFZFNpeWZUSG1aMjRZa0VDZ1lFQXhXY3QKS1JpOG5ZampLbmxQWHZLTVhxaDFJZjdmemdjN0E1Ky8rTVppT0lHMUhib0VZbmwvU3ZmZWRlU29jdzIzNzA2MgpaSm5kaFJJVlU4NnVndVFyaUVCenNZWkkxU3NwUnNnS0thR2dOdW8wY2xPYkgzUjIydElHcGRyamxyMDRoSkpQCkRaQVRpa0Z6bTU5MzhxN3BUcnAwbVV0bnNvSzRiaW96MWx0bklqOENnWUFKYWNzV3ZmV2FoNUFvNXdiRjBHTHQKalJ5UlgxS3VIQmJtTVg0MmQ4ZVJ6M0JoOFdnOGpCT2ZXUmhHbnBzbzRnY01DTXpEYmQzd1I4OGtQbXgrOUN4OQpHczNZYUh3U09MRE9TRmM4REIrc044QkpLSEJpZTdhQlBOUUd6cXEzN2hkNCs1czBoV1hIaVFnV0hvNUViUmJxCnRYcXhtYTVCd2dvSWlWOTlya2FCZ1FLQmdRQ2F3VUpFUDZKT2xpd1E5ajFsSUIwdmNEMHp3NDVOQkpvZFF6WEsKb0N3TVJKTnFsSlF2WnpTRjMwQkZpRmNPcTZpUk1WSUlHQi96M0xOZm1sNXhPdE50NDZsUGVRak1KU1FjMjJJcwpOUXRYUWRDUCtEOWFMNEdtRnBVam5UQmkwa0RtblpUZit4MTJlODFZS1hKdGpQWld4Qkg0dzRhdUNaRHd2S0VhCjRWay9IUUtCZ1FDL0RsNHdOdWM4WnB1Q3FsVTI4Mkt5ZmtJaFhvakZJdHFYZTRsZW9GMVBBcEQ3MTlaVWs1L2gKUGJQNWtQNUtGS2NsaHV5b2RONXFwcUdCUkxibFdueFA1bzdJMXBhb3ZDL29aZDk5WjVQRUpNSDdNQXMzZytZSwpBdGVVM2NvMUhkcENtdkhOdFFvY0tEMlliTzF6bTNMbG9wQTRkY1dhRDducyt6Q0UvWmFUblE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo='); -- REPLACE INTO
MERGE INTO `aio_operator`(id, op_code, op_name, op_status, programming_language, algorithm_code, algorithm_path, input_param, output_param)
    VALUES ('1', 'sum', '两数之和', 'PUBLISHED', 'python', '# import start

# customer code start
def main(input_data):
    a = input_data["a"]
    b = input_data["b"]
    return {
        "product": a + b
    }
# customer code end	', '', '{
    "a": {
        "name": "参数a",
        "type": "int",
        "required": true
    },
    "b": {
        "name": "参数b",
        "type": "int",
        "required": true
    }
}', '{
    "sum": {
        "name": "两数之和sum",
        "type": "int"
    }
}'); -- REPLACE INTO
MERGE INTO `aio_operator`(id, op_code, op_name, op_status, programming_language, algorithm_code, algorithm_path, input_param, output_param)
    VALUES ('2', 'product', '两数之积', 'PUBLISHED', 'python', '# import start

# customer code start
def main(input_data):
    a = input_data["a"]
    b = input_data["b"]
    return {
        "product": a * b
    }
# customer code end	', '', '{
    "a": {
        "name": "参数a",
        "type": "int",
        "required": true
    },
    "b": {
        "name": "参数b",
        "type": "int",
        "required": true
    }
}', '{
    "product": {
        "name": "两数之积product",
        "type": "int"
    }
}'); -- REPLACE INTO
