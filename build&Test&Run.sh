#!/bin/bash

#compile
javac -cp ./src:./out:./Junit/junit.jar -d ./out ./src/View/*.java ./src/Controller/*.java ./src/Model/*.java ./test/Model/*.java

#executez test
java -cp ./out:./Junit/junit.jar:./out/ Model.AuLongCoursTest
java -cp ./out:./Junit/junit.jar:./out/ Model.CategorieTest
java -cp ./out:./Junit/junit.jar:./out/ Model.PonctuelleTest
java -cp ./out:./Junit/junit.jar:./out/ Model.TacheTest

#go to out folder
cd ./out

#execute MainView
java $1