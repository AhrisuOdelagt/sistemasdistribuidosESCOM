#!/bin/bash
cd source/
javac -d ../classes *.java 
cd ../classes/
jar cf Applicacion.jar WebServer.class Aggregator.class networking/WebClient.class
java -cp Applicacion.jar WebServer
