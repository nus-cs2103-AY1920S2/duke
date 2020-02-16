@ECHO OFF

REM delete output and data from previous run
if exist ..\src\data\data.txt del ..\src\data\data.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out\production\duke\ umaikaze.duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT > DIFF.TXT