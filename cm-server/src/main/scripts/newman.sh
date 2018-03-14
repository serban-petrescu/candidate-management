#!/bin/bash
cd $HOME/node_modules/
node newman/bin/newman.js run cm-server/src/test/resources/postman/candidate-management-tests.postman_collection.json