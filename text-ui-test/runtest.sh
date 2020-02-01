#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
  mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
  rm ACTUAL.TXT
fi

if [ -e "./data" ]; then
  rm -r data
fi

# compile the code into the bin folder, terminates if error occurred
if
  ! (
    find ../src/main/java -name "*.java" >./sources.txt
    javac -cp "../src:./gson-2.8.6.jar" -Xlint:none -d ../bin @./sources.txt
  )
then
  echo "********** BUILD FAILURE **********"
  exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp "../bin:./gson-2.8.6.jar" duchess.Duke <input.txt >ACTUAL.TXT

# compare the output to the expected output
if diff ACTUAL.TXT EXPECTED.TXT; then
  echo "Test result: PASSED"
  exit 0
else
  echo "Test result: FAILED"
  exit 1
fi
