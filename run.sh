#!/usr/bin/env bash

mvn clean install; java -jar target/demo-0.0.1.jar &
PID_JAVA=$!

cd react-frontend && npm i && npm start &
PID_NPM=$!

trap "kill $PID_JAVA $PID_NPM; exit 1" INT
wait