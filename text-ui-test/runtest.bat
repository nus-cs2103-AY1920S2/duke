@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM compile the code into the bin folder
REM javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\Duke.java
REM https://github.com/nus-cs2103-AY1920S2/forum/issues/3#issue-551755325
dir /s /B ..\src\main\java\*.java > sources
javac  -cp ..\src -Xlint:none -d ..\bin @sources
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    del sources
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input file and redirect the output to the actual
java -classpath ..\bin Duke < input > actual

REM compare the output to the expected output
FC expected actual > fc_output || cat fc_output

REM cleanup
del sources
del actual
del fc_output