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

# compile the code into the bin folder, terminates if error occurred

if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/sample.Main.java ../src/main/java/commands/add/*.java ../src/main/java/commands/*.java ../src/main/java/common/*.java ../src/main/java/data/exceptions/*.java ../src/main/java/data/task/*.java ../src/main/java/data/*.java ../src/main/java/parser/*.java ../src/main/java/storage/*.java ../src/main/java/ui/*.java

then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ../bin sample.Main < input.txt > ACTUAL.TXT

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
