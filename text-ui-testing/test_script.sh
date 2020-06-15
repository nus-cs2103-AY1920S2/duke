#!/usr/bin/env bash

# -----------------------------------------------------------------------------------------
# Step 1: Compiling
# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/Main.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# -----------------------------------------------------------------------------------------
# Step 2: Testing
for dir in */;
do
    # Delete output from previous run
    if [ -e "$dir/actual.txt" ]
    then
        rm "$dir/actual.txt"
    fi

    # Run the program, feed commands from input.txt file and redirect the output to the actual.TXT
    java -cp ../bin Main < "$dir/input.txt" > "$dir/actual.txt"

    # Compare the output to the expected output
    diff "$dir/actual.txt" "$dir/expected.txt"
    if [ $? -eq 0 ]
    then
        echo "$dir: PASSED"
    else
        echo "$dir: FAILED"
    fi
done
