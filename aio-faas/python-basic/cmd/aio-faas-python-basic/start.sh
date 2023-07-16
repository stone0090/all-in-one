#!/bin/bash
set +e
nohup /bin/bash -c 'python /home/admin/aio-faas/python-basic/flask_app.py --mode prod' > /home/admin/logs/aio-faas-python-basic.log 2>&1 &