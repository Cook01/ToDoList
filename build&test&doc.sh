#!/usr/bin/env bash

#compile
javac -cp ./src:./out:./Junit/junit.jar -d ./out ./src/View/*.java ./src/Controller/*.java ./src/Model/*.java ./test/Model/*.java
#executez test
java -cp ./out:./Junit/junit.jar:./out/ Model.AuLongCoursTest
java -cp ./out:./Junit/junit.jar:./out/ Model.CategorieTest
java -cp ./out:./Junit/junit.jar:./out/ Model.PonctuelleTest
java -cp ./out:./Junit/junit.jar:./out/ Model.TacheTest

#doc
javadoc -encoding "utf8" -docencoding "utf8" -d doc/ -author ./src/View/*.java ./src/Controller/*.java ./src/Model/*.java
