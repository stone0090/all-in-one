#!/bin/bash
cd ..
python setup.py sdist
pip3 install dist/aio_sdk-latest.tar.gz