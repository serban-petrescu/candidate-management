#!/bin/bash
cd $HOME/.m2/repository
ls
node node_modules/newman/bin/newman.js run cm-server/src/test/resources/postman/candidate-management-tests.postman_collection.json