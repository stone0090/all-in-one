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
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('1', 'operator_algo_code_template', '# import start

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
        "name": "参数",
        "type": "int/double/string/boolean",
        "required": true,
        "defaultValue": 2
    },
    "b": {
        "name": "参数",
        "type": "int/double/string/boolean",
        "required": true,
        "defaultValue": 3
    }
}'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('3', 'operator_output_param_template', '{
    "product": {
        "name": "两数之积",
        "type": "int/double/string/boolean"
    }
}'); -- REPLACE INTO
MERGE INTO `aio_config`(id, config_key, config_value) VALUES ('4', 'default_k8s_kube_config', 'apiVersion: v1
clusters:
- cluster:
    certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJek1EZ3lPREUwTWpJME1Wb1hEVE16TURneU5URTBNakkwTVZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTlZYCnRrYUtoeDZuOFJaZmR0SnE3VXRsTWE1K3lCT3dEVU9Nci9PNWh2STF5cWphZFArRnZ0N2RGb0tSZllFNkNaOWcKZEdHYnRvVERPVWtmdzJ4RGFhSDJIaFhPNmRKcVhRMElmKzBseldEUFZwWitQb1lyaTI2UDVQYmxLbVNJZE96UQppaCthMERaN3ora3BNMUhnMjVSVEQ2L1lYb0lCNkdBVk1pVGxvR2h3czVmNE15Z3Uzcyt1bGp3bDF4Q1h2eUV1Cmt6Z1F3b3lueVlpbHBzSDZSZ3lEb2NnODZPWXJNbDhZaUpGVmFLVm1LZHlBVElDeUtSaGRRdkt6UmhzN2tZaWwKSXNUaG9kNTVkcENZTGZVOWxncndCL1o4TXNxRTJTbzI5TXBtWEJ5UGx6d0Fkc2RDb1RiWFFHT2FRbWRHRUc3TQpGWDFvUWpzNVUvU3JnMFNtTy9jQ0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZBcjZtSVZ3SGFDWlg1QTRRem1MRSt6S1JydlZNQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBSmpyWEF3YkYzSCtUTTJqZE1USQpuNXdLNEFWYnJHTWxMcEpwOXdrYW0reHhsQnVHQmgvTFgraThIc2dKUTV6dHhjTEludkxaWVNVSGNneExDNnRjCjZzVDFkYWs1ajJra2dTZy9rK244dUs2RWdtUjAyZElpZU9vcFBBb2NzVEl0YUlUZ0xockhUQjFVdEp4T1NRWUoKWVdiaGJNMFY5bU5VSGR5NU1reEpIYkQ5SytRQTQ1YlN3RmFrTndmbDEvcDdwOXFuNHJqQkRwQTRQVTc4Z2xQRQpObW1XbkxhUDlxS0xrNW5PYWZwMXpzd2g0RjRXM0lqZVkycEZvSnlFTU8yZDZXbWdubzNmWWNwQWRnWEhocE0rClhsVG9ZMGxmTlZxdk9XaDFDMUE5d05nYWN5NXlob1JlUFBkdWJ5c0Z6WS9VWFZRTlFzQnpkRTdzdDFYN2hPTTgKV3Y4PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
    server: https://kubernetes.docker.internal:6443
  name: docker-desktop
- cluster:
    certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM2VENDQWRHZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQ0FYRFRJek1EVXlPREV5TkRneE5sb1lEekl4TWpNd05UQTBNVEkwT0RFMldqQVZNUk13RVFZRApWUVFERXdwcmRXSmxjbTVsZEdWek1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBCnZHOCtSYmlkY3QvYzlhMFRPc3lNelZpOVVvS2hmWklkZk1XRzFENUJKTW1aN2ZIT3R1bUVwMkYwWkFSbVB4Mm4KekZDbHAweVVwNGxGSTh6K3lyWUFKNXQ5S2t6b1RGQ2Nmd3JtdDlSZ25OdDFkYVlRZ1k4NUZkLzF0eXVQV2d4RwpWckFWTEJFSHpyTUd0QWJ1RG9MVmlGaFdaME91dHVMclZxQmVJeGltTXptTUJYbk5aQzRMZ3JHZVNKd3JyZDNZCmhvMjZnb0E5a1ZoaXlqQ3dGa0xuUW1OUnFNN1BWbnRYb0EzYkR6L0I5VjJYTzRxVGVmMy9PMXp5ZjNlN1lIYk8KcUtJUGg0SWJiQ1R2NEVPTDdKcUxDeGJIMkJmVXg0M2xteVJUV3E5bU1sTXI2c0Rjdk9abG5QQkE3WS9ZZVI3ZApnOXc1VTRjZmQzV2FrclBKS05Ka2lRSURBUUFCbzBJd1FEQU9CZ05WSFE4QkFmOEVCQU1DQXFRd0R3WURWUjBUCkFRSC9CQVV3QXdFQi96QWRCZ05WSFE0RUZnUVUxNTc0b3p0UWdTREtWdlpqNFJHSG5mSGtyemt3RFFZSktvWkkKaHZjTkFRRUxCUUFEZ2dFQkFINXA1b3dGWU9hdThjVGg5U1hyOG9udDFnamFTaTBEOHZxTVpLWCs4eGhOY0t0Tgpta1M0THVkQWZadDNmRnlaRXB4anh3TmF3V0VtTFFGa3liTmloVUhwM2hPSlBNRmlIZC9LY0J0S0NSNmgxNWtGCkZUVjBEUjF6TGI3VG5aeXQ5RmVXV1JIUVZPVzBUUmwvSFAyV2U3UWIrVThmemJndmVOR3Rsb0Y0NWduRTRqNGYKRjRYZnllcHZlT05aSGF6QVFUS3pFdnA5VXpmZXJKT0ZHWkRZcG1Ua2FPVmQrdTBRcnZWZ3JVZU16TXdiSUdGUgp4dm9OeStjVGhQQTFrTG1uMlN3aFE5dklSdDFtbHJyZXRZUGdna01NTExGbTZxckRaNlBZQXBMcFZDenl0MEdOCmR6ZWhrb0k3bzFzWC92M3N1d3p4TFhzQmsrRW9GU29HTHNnMXg0cz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=
    server: https://apiserver.cluster.local:6443
  name: kubernetes
contexts:
- context:
    cluster: docker-desktop
    user: docker-desktop
  name: docker-desktop
- context:
    cluster: kubernetes
    user: kubernetes-admin
  name: kubernetes-admin@kubernetes
current-context: docker-desktop
kind: Config
preferences: {}
users:
- name: docker-desktop
  user:
    client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURRakNDQWlxZ0F3SUJBZ0lJTXV1ZUNnREtIM293RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TXpBNE1qZ3hOREl5TkRGYUZ3MHlOREE1TURVeE5qUXpNVEphTURZeApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sc3dHUVlEVlFRREV4SmtiMk5yWlhJdFptOXlMV1JsCmMydDBiM0F3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3RVc2w0MnY3OER4ZmEKNXlHcTNOTUdwb2NvNHdGWTlsakRRcFRsTksyQ0drZ0pKL2poM25JdVVzUWpuRzNRdlBVbkZGUFBUVWRuWVhibQoySUhNNXZwdDN2RXlLaDRkMXRDSml4aUszOGY4RHNhRHdQWXlSV3lQZitweWlGQlFQekJ0VUozcXR4TzN0SStWCkpocW05OWRvb2daSXRwWGV6Z3pnTS9pT29qSTVrajJsTTQyTHhvdUxZL0FDTC9DUDBVYjN3ZnNuS243N3pyL0oKc3IvbzRlYkZyZWFoRXpsZERkTDcrQTJGYTE3V05FYTJvVDBRME1xa09jajA3M093OEI2UnFNSVVEeE9lN2NGWQpMbmZMUmFhNEFiTTJxc1lOQXRhRFFzcVM0QWxMYmRQangydWlDLzVhNTBDSmNsbThPenlUaFhXZWxHYTlxRlRZCmRnbnFVeG1yQWdNQkFBR2pkVEJ6TUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQU1CZ05WSFJNQkFmOEVBakFBTUI4R0ExVWRJd1FZTUJhQUZBcjZtSVZ3SGFDWlg1QTRRem1MRSt6SwpScnZWTUIwR0ExVWRFUVFXTUJTQ0VtUnZZMnRsY2kxbWIzSXRaR1Z6YTNSdmNEQU5CZ2txaGtpRzl3MEJBUXNGCkFBT0NBUUVBU3IrcHVQd1EwTldrYStyajhLR0RmaUlGVlFmZzA3cjNsZ2piNnRyY0pLZGVsKzFKY0RVLzJsRWcKbk1lWlNUTFQ5RVVOL3ZQd1p1aFBray9NcDduWldISlBDbGlIZG80VCtvdmwvOU1qTmFrS1BjdURUcUNHTG92YwpzQ2oxMDlDMkp2WHBsaUZZMHNjVTBlYkdOalZ1Wjl5T1I2bm9neEI1WGVtUHpKeHZhNTBHL0FrdjNmMXhVelhKClc2RFlaK0d6RzlCeVJtSDdsZnk2MWZkUkFFS1pLNHE3cjM3Ly9JVmtiQURuZlVVQzhIWTk0VFduRXFtR1l1eS8KdlE2L2p0ZjB6UzRhUU82MFFUOVV5QUxsQkZnS0sraXV4eFk5TFlHNTV5eEF6R0lqa2hZTGJvY21RcVVNK1hzSAplU0ZLWDY0aTVEWHZubzc1d0ppT3VndUk0N0xERUE9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
    client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBclZMSmVOcisvQThYMnVjaHF0elRCcWFIS09NQldQWll3MEtVNVRTdGdocElDU2Y0CjRkNXlMbExFSTV4dDBMejFKeFJUejAxSFoyRjI1dGlCek9iNmJkN3hNaW9lSGRiUWlZc1lpdC9IL0E3R2c4RDIKTWtWc2ozL3Fjb2hRVUQ4d2JWQ2Q2cmNUdDdTUGxTWWFwdmZYYUtJR1NMYVYzczRNNERQNGpxSXlPWkk5cFRPTgppOGFMaTJQd0FpL3dqOUZHOThIN0p5cCsrODYveWJLLzZPSG14YTNtb1JNNVhRM1MrL2dOaFd0ZTFqUkd0cUU5CkVOREtwRG5JOU85enNQQWVrYWpDRkE4VG51M0JXQzUzeTBXbXVBR3pOcXJHRFFMV2cwTEtrdUFKUzIzVDQ4ZHIKb2d2K1d1ZEFpWEpadkRzOGs0VjFucFJtdmFoVTJIWUo2bE1acXdJREFRQUJBb0lCQUdRcXF4cjRYVkpvbXAyOAo0LzB0LzVzaFZJMXhtODdCR0ExSWhtcHNaallCWUU1Q1ZwaWFpUnh5WXI0OTRrMDNWdXJOMloyOWlKMmJleTd3ClF4dWJCWEhIM1ZiamRtSkNTbVNqQ1gwQTFUKzdma2lZbE8vNnBVSE5hRDNtdjV1UTJUamoxVS9YbkVLWHpEOGoKTlZYM2Qvc1ZZRkpYaXdlbngxTXZWZCtBcDB3VFh3ZGIvUkZvZ0tDeWNmS0F3UHgzVUNUM0dWS2piRktkS0phdwpQZTlHUWFJTkNnbVdiREMvTXoyejgyYlJzUmNuVUVpcytEM1JQYW1kL3VFa1hpYjFOdE0vck5aaUw3RVJVQ3pHCkFNSGR2WHNqaFRvTDFRRzZGUkFkSjlDMWNIWTRwdXpObzZHQzNiWUFaRXNNclJTc3gwelJXYkF0M3k2QUt6T2wKczF2NnRZRUNnWUVBdy9rTWpta2kxN0ZDWmJWOXk0ei9wUWZ5NW4zSElFSTh6Qy9rYmpkTGVsY0hCQ1RDNDBBZgpDUndhc1E2cm9EWm9pM3A2bnJITXhKbVhCNWZ4OThJTjVnaExLMUxUMjAxRjFJUi9pUmdwcHdBVFpMZXhNZ0luCk9IR1JDSlN6WkdrS2xVc2E1MVlna3RhUFdqLzRORTVHL0lXdGlUdmpEcUREMVJsS0Uxd0U2K3NDZ1lFQTRtbTEKNHluZjVGSHZaYVU5NWcvdnJDc0plK05YbWt5VHAwcTRHUXJhZVgwYTRpUzJqSEsxcGIyNUttYWtPT2RZUEhFSQpPdnI2WnZYOU5Xd2l0Z3VrSFBoT1FJQjQ4enVVcmkwcVlHaVJJWjl4VjZUYU5ZVU1RQndnLzhkTHRTZmRDc1YvCkM2all4dWFMbDBwc2NkYW5OQ1A3Vm1laFBkdXNBSHoxNGpvTDJVRUNnWUVBdWhpVUhlQWs0Q0pKNVArMTEwZXIKcy9HYWJkcGU3M2syOXNjNzZwWERyUWRiZUVuWWI2aUNVRXBKNW4wQjZZQUJRd0lwMHUwUWdGbEJuRXBqMGx5Swppaml4K1JBTWNxSXNmNlRhM3pyOHdzTkZwMHp3allhUjYvMmNOa1BQSHJqSS9nQjJLZHdWenByRlFuU2dwRHI1CkdlOXZYWFJySjRidXNEdjFwZkNxRThVQ2dZRUFtS0J3UjZGTDE2dFFPWElxQnZQSk9vT01raUpRUktqSWhHYS8KTWNwNk9wbVdUTW80OFBiRzh1S3hkeDdZSjYvMGNGSTJIU1JoTnBVdXl4dUpUclVNUGFjNWdRdlovUVcyRmJCRwp0OVRsdVFlMXJVSnBBdGtjeC83dVU5NXliNzFlcWNzMitKSXJCL0lVT2JtbTN4cTNmbE9mTThFS0Zod0dtQkdrCjQ0c0R2OEVDZ1lFQW9RN1VjSFJpVFRER01PT05TWER5T1FZRlJvT1dJdVlTWFZ0bVEyVTZ1T0pLb0lCZ2UrbCsKNGtOVWdmUjkwYmZZOWZlRTR1bHk5UUdHRi9sbFNzcXdqL2NvZGFITWpCS1Y2Tk9CeWwzOGVaeG1TQXJUaEQ3QQpYSnBzWndMZjNESU1qS2wxeG8yRGp0L041OXMwSmRaanMvdzFib2tHRkd3ZFhjM0FWaGdxWUVnPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=
- name: kubernetes-admin
  user:
    client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURGVENDQWYyZ0F3SUJBZ0lJUXg1dEhVUnBnSzh3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWdGdzB5TXpBMU1qZ3hNalE0TVRaYUdBOHlNVEl6TURVd05ERXlORGd4TjFvdwpOREVYTUJVR0ExVUVDaE1PYzNsemRHVnRPbTFoYzNSbGNuTXhHVEFYQmdOVkJBTVRFR3QxWW1WeWJtVjBaWE10CllXUnRhVzR3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQzZFcWJJMnk4Y3JpQ3AKWVU4WDlkeEllVDRha3BxamdOb2pOVkdMclNOazdGWkxhaHpsSEhNMzNWcFI0R0prWk9CK2ZHMXhCYlFkQ1JwaApQVFZJOFIzZ2hVWjdvckFsOWhCSHBtNFZvWGNYTnE1dWVLRXdjSGFPbDJMTk5xZTlRK2ZTS2JocEpKTndMaUxVCjJXVXcrK256MWlHYWtGMjZnZzdLQTlWQ28wMnpCVDFIdEhPN3NFblFySjNWd1BZd1RNZXMyTllYUStOZFczV00KZExnU3BKSzBuZDRsRWNrSDE5UTlYMHNtSWJmbXBRS3JaOGZNTFVlWWxXMWJSL1RMM1FWTFpXNlluaUs4bGlGQgpzUzJHR0JESHNWRmZ2bkpiYmtnZjR1aXY4cUxOTk1DYmtZZ1lnWFQyem1KSmVXZFZURDdyUGYxaFZwbnhITkFaCmtYRXhueFpaQWdNQkFBR2pTREJHTUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQWZCZ05WSFNNRUdEQVdnQlRYbnZpak8xQ0JJTXBXOW1QaEVZZWQ4ZVN2T1RBTkJna3Foa2lHOXcwQgpBUXNGQUFPQ0FRRUFtRlFmU2kwMitwdjB1aTU3UnpIT3psLytkRjREQnF4NlNJK2hxN3h4VjB5MXJXd1dCWm1TCmxUMGZ2UmQwRUVaWWVwL2RlRXpNUVNsak8vTTh0TWtZWmZDY3dPSlg0S2FjUjRRSlNaR3djOXRmUlQwKzdvbHAKcnJMZUpJRFNRTUFEN1ZFNVRwY3RaNmxBVjFTYkNXQmNpZnBEUjJ5VllJWEtNRVY2WUV0bHZKamRJL1dscm9OUQo3bVJLVFd3OERnL0N3TDdqQlVqZHdITk9kY2VVYVYyUE94VzVoYnE4NDdzRDBEdnl4ODcxMWFEOTByb0NlY0wyCmNuTnQrZitURmRJWG5EWnREcXFRdmdBeDliMVR2dk1JYVQyQmZTMk9sWTlqTEJhVlBKbkVJK3ZWcjlOWWo5TzcKa0REY1I5WE14VERnZm90dkFOT2wxNmVMSE91WUJWTkNJdz09Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K
    client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBdWhLbXlOc3ZISzRncVdGUEYvWGNTSGsrR3BLYW80RGFJelZSaTYwalpPeFdTMm9jCjVSeHpOOTFhVWVCaVpHVGdmbnh0Y1FXMEhRa2FZVDAxU1BFZDRJVkdlNkt3SmZZUVI2WnVGYUYzRnphdWJuaWgKTUhCMmpwZGl6VGFudlVQbjBpbTRhU1NUY0M0aTFObGxNUHZwODlZaG1wQmR1b0lPeWdQVlFxTk5zd1U5UjdSegp1N0JKMEt5ZDFjRDJNRXpIck5qV0YwUGpYVnQxakhTNEVxU1N0SjNlSlJISkI5ZlVQVjlMSmlHMzVxVUNxMmZICnpDMUhtSlZ0VzBmMHk5MEZTMlZ1bUo0aXZKWWhRYkV0aGhnUXg3RlJYNzV5VzI1SUgrTG9yL0tpelRUQW01R0kKR0lGMDlzNWlTWGxuVlV3KzZ6MzlZVmFaOFJ6UUdaRnhNWjhXV1FJREFRQUJBb0lCQUY3ZHhZVGdJdXZuWnlXVQpSQ1ArS0pvV25uQWowSTJOMHlsaU5Mb1d3dFhnbkxxRVZra0ZNVVVKS05SWHB6SnlML2FzNzR0UkVody9tT0I4CnVWNy85M0NSNG5hRVluUm9PTEtmQ3I4aWZEb3NtZEFlMW1lQTN5RWgwN0MycmR5VDgydzE4eHhBRnA5NkZpNFkKekR1bUpBZzZZeFdQaHduZzV6TmRzdXZ2b0Z5bGIyQ3p5SUhQSnM2SHNzVnVZVkpGMkZzeW1lYUw1YWZpUFBlQQo3MlFEcVhTM3dYRVJnNkFGRWIrQUFyYWNMTmsxRGhlWlZlZ3kvN1Z3SlBIRTNwa2NFWk1kdzlacDdkdVhHencrCi8zTWpJeHVJbkJFSzB2Ukd0cE4rMkZGdEFBK3QyUmRxYWxtUE1WK3ZGQnFLcHI5NjZqQ3FnejhHdEhzQUNjdW0KU09KZlFvVUNnWUVBNVRabzFCZVhzUEFrUUxqRTVRMVRFRGlkcjR6YUlrdWNkb0MxMXYrb3VGQTRmblJ3TWl5QgpsaDFVVXZtUGJ1akkwZFZycmY5RkNJWUh0WEVVa2I2bFNlbnNyUjVYdnhQMzRoZXNDYnVnVHpIL042SnlQRDFlClFUeUJoK1VyQlJ4YW1teGgwNC8wSWh4TkxHeVF2VVRTMUs4SjQ3SmtsUW45OHAvblZOVkFTMThDZ1lFQXo5R1cKVk1RMWRqSHZLbnZadXFscjVzK0RRdmpacU1TandLREtDUkdNK0ROdHMya2NGRHg1WDVCblFxL2E5ZHdBL05EUApVbTU0VDAvWkpSSlRrQzNiNnF6SWsxcFh3NUJPMGNDbHA0NDZJNWFDa0JXbFBZQ2w2ZVVRRHZDeDdBaTNrdDc2CjIwODFNdzBCMGpYVEdpTjJhQTRIWHo5VWpXQUVuaVZvaGN2RE1VY0NnWUFKVG9WQkgxZ2NhaE8rd1FIVm53M2gKV2JzbFZwM0F5THNvb0o5QmhJM3dOZW1sVU1zQmJvY3dkaTVuVkJTNzF4M1lUMGNaQUlWb0RERWg3OTU2OEhlNwpvWkRua3BUVGdGY1BTaERGTUdZWThSbkF3NHJKNFdBRFhCYVNVdjdGTFZxTWJKd29QKzhVdDN0MnMwM0pNWk14CjZZMklQa1VSUEIrZ2kxZVVQVGhvRndLQmdIZUhObzNlcEZrczgwQmVKNmJDMXpyVDA4MXJUOVZQOUZkUW82Ti8Kb3JNRzhXdWxZdThJc1U2VHdiQ0xjTWJFR1JWUURFNS9GMWVXdDNBcEVFTDIvV3dHb1A2UGtiYjdnem9GTWNJagpmYkVHV21ZQTdSOW9wUVRZS05wTytpYW1CTi9HdkVYelVzZmdEVklZZnlpamlQRGtVTDBwc2JuMnBmRkgxc3hOCm5DRDFBb0dCQUkxRHRFaU5ZNWhQbzZlcVVmek13YitkYXcwN2ozcnNqNURaTk5IM3ptSzBneGxKYTlyYjhoaG8KZUd1QmZRRlFzRGdvTE5DRWVzaEtYaUxjOVZpOUtxeUJMU3JXc2d4RWN5M3ZwVXF2akVQTUxIYWVnMU9LcE1vSgpzdXZVQ2NRYTdHN0k4RVNuekpxODVGdEx2bW9wV3U3Z2xjaWszQjA4emYzV29QdWJsZ3IrCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==
'); -- REPLACE INTO
MERGE INTO `aio_operator`(id, op_code, op_name, op_status, programming_language, algorithm_code, algorithm_path, input_param, output_param)
    VALUES ('1', 'sum', '两数之和', 'PUBLISHED', 'python', '# import start

# customer code start
def main(input_data):
    a = input_data["a"]
    b = input_data["b"]
    return {
        "sum": a + b
    }
# customer code end	', '', '{
    "a": {
        "name": "参数",
        "type": "int",
        "required": true,
        "defaultValue": 2
    },
    "b": {
        "name": "参数",
        "type": "int",
        "required": true,
        "defaultValue": 3
    }
}', '{
    "sum": {
        "name": "两数之和",
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
        "name": "参数",
        "type": "int",
        "required": true,
        "defaultValue": 2
    },
    "b": {
        "name": "参数",
        "type": "int",
        "required": true,
        "defaultValue": 3
    }
}', '{
    "product": {
        "name": "两数之积",
        "type": "int"
    }
}'); -- REPLACE INTO
