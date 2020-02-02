#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./output.TXT" ]
then
    rm output.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../../ -Xlint:none -d ../bin ../../main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input.txt > output.txt

# compare the output to the expected outputr
diff output.txt expected.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi