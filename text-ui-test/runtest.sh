#!/usr/bin/env bash

 # create bin directory if it doesn't exist
 if [ ! -d "../bin" ]
 then
     mkdir ../bin
 fi

 # delete output from previous run
 if [ -e "./ACTUAL.TXT" ]
 then
     rm ACTUAL.TXT
 fi

 # delete saved tasks from previous run
 if [ -e "../user/data/tasks.botstore" ]
 then
     rm ../user/data/tasks.botstore
 fi

 # compile the code into the bin folder, terminates if error occurred
 find ../src/main/java -name "*.java" > sources.txt
 if ! javac -Xlint:none -d ../bin @sources.txt
 then
     echo "********** BUILD FAILURE **********"
     exit 1
 fi

 # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
 java -classpath ../bin bot/Duke < input.txt > ACTUAL.TXT

 # compare the output to the expected output
 diff ACTUAL.TXT EXPECTED.TXT
 if [ $? -eq 0 ]
 then
     echo "Test result: PASSED"
     exit 0
 else
     echo "Test result: FAILED"
     exit 1
 fi