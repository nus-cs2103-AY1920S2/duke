@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete storage in dude.txt
del ..\data\dude.txt

REM delete output from previous run
del ACTUAL.TXT

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
cd ..
gradlew run --console=plain --quiet < text-ui-test\input.txt > text-ui-test\ACTUAL.TXT
cd text-ui-test

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT