#!/bin/bash
cd ..
node node_modules/newman/bin/newman.js run cm-server/src/test/resources/tests.postman_collection.json