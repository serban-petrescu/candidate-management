#!/bin/bash
cd $HOME
ls | grep newman
node node_modules/newman/bin/newman.js run cm-server/src/test/resources/postman/candidate-management-tests.postman_collection.json