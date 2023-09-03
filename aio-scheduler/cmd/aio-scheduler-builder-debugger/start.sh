#!/bin/bash
set +e
dlv --listen=:2345 --headless=true --api-version=2 --accept-multiclient --check-go-version=false exec /home/admin/aio-scheduler/aio-scheduler -- -mode prod