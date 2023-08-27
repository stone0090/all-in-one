#!/bin/bash
set +e
current_path=$(pwd)
#current_folder=$(basename "${current_path}")
flask_port=$(python parse_config.py)
target_path="${current_path}-${flask_port}"
#target_folder="${current_folder}-${flask_port}"
target_log_path="${AIO_FAAS_PYTHON_LOG_DIR}-${flask_port}"
cd ..
cp -r "${current_path}" "${target_path}"
mkdir -p "${target_log_path}"
nohup /bin/bash -c "python ${target_path}/flask_app.py --mode prod" > "${target_log_path}/start.log" 2>&1 &
