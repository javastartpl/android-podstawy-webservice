#!/usr/bin/env bash
./mvnw clean package
docker build -t android-podstawy-webservice .
docker tag android-podstawy-webservice javastartpl/android-podstawy-webservice
docker push javastartpl/android-podstawy-webservice