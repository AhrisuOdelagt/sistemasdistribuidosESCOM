#!/bin/bash
cd source/
javac -d ../classes *.java 
cd ../classes/
jar cf Applicacion.jar Application.class Aggregator.class SerializationUtils.class Demo.class Coordenada.class PoligonoIrregular.class CompararMagnitud.class networking/WebClient.class
java -cp Applicacion.jar Application
