@ECHO OFF

REM create bin directory if it doesn't exist
if not exist C:\Repos\DukeProject\bin mkdir C:\Repos\DukeProject\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp C:\Repos\DukeProject\src\main\java -Xlint:none -d C:\Repos\DukeProject\bin C:\Repos\DukeProject\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath C:\Repos\DukeProject\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

cmd/k