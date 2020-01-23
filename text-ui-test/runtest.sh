#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir /Users/sanchari/Desktop/JAVA/duke/bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp /Users/sanchari/Desktop/JAVA/duke/bin -Xlint:none -d /Users/sanchari/Desktop/JAVA/duke/bin /Users/sanchari/Desktop/JAVA/duke/src/main/java/Duke.java

then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/sanchari/Desktop/JAVA/duke/bin Duke < /Users/sanchari/Desktop/JAVA/duke/bin/input.txt > /Users/sanchari/Desktop/JAVA/duke/bin/ACTUAL.TXT

# compare the output to the expected output
diff /Users/sanchari/Desktop/JAVA/duke/bin/ACTUAL.TXT /Users/sanchari/Desktop/JAVA/duke/bin/EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi