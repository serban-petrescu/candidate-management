#!/bin/bash
cd ..
node cm-client/node_modules/newman/bin/newman.js run cm-server/src/test/resources/postman/candidate-management-tests.postman_collection.json