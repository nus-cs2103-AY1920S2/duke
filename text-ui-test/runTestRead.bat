@ECHO OFF

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out\production\duke\ umaikaze.duke.Duke < inputListOnly.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT > DIFF.TXT

@pause