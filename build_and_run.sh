#!/usr/bin/env bash

# -----------------------------------------------------------------------------------------
# Step 1: Compiling
# Create bin directory if it doesn't exist
if [ ! -d "./bin" ]
then
    mkdir ./bin
fi

# Compile the code into the bin folder, terminates if error occurred
if ! javac -cp ./src/main/java -Xlint:none -d ./bin ./src/main/java/Main.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# -----------------------------------------------------------------------------------------
# Step 2: Testing
java -cp ./bin Main