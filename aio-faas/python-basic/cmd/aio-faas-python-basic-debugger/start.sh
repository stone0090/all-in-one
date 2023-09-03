#!/bin/bash
set +e
cd "${AIO_FAAS_WORK_PATH}" || exit
service_port=$(python parse_config.py)
target_path="${AIO_FAAS_WORK_PATH}-${service_port}"
target_log_path="${AIO_FAAS_LOG_PATH}-${service_port}"
cd ..
cp -r "${AIO_FAAS_WORK_PATH}" "${target_path}"
mkdir -p "${target_log_path}"
nohup /bin/bash -c "python ${target_path}/flask_app.py --mode prod --port ${service_port}" > "${target_log_path}/start.log" 2>&1 &