# Setting up

**Prerequisites**

* JDK 11
* Recommended: IntelliJ IDE
* Fork this repo to your GitHub account and clone the fork to your computer

**Importing the project into IntelliJ**

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
1. Set up the correct JDK version.
   * Click `Configure` > `Structure for new Projects` (in older versions of Intellij:`Configure` > `Project Defaults` > `Project Structure`).
   * If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
   * Click `OK`.
1. Click `Import Project`.
1. Locate the project directory and click `OK`.
1. Select `Create project from existing sources` and click `Next`.
1. Rename the project if you want. Click `Next`.
1. Ensure that your src folder is checked. Keep clicking `Next`.
1. Click `Finish`.

# Tutorials 

dukeclasses.Duke Increment | Tutorial
---------------|---------------
`A-Gradle` | [Gradle Tutorial](tutorials/gradleTutorial.md)
`A-TextUiTesting` | [Text UI Testing Tutorial](tutorials/textUiTestingTutorial.md)
`Level-10` | JavaFX tutorials:<br>→ [Part 1: Introduction to JavaFX][fx1]<br>→ [Part 2: Creating a GUI for dukeclasses.Duke][fx2]<br>→ [Part 3: Interacting with the user][fx3]<br>→ [Part 4: Introduction to FXML][fx4]

[fx1]: <tutorials/javaFxTutorialPart1.md>
[fx2]: <tutorials/javaFxTutorialPart2.md>
[fx3]: <tutorials/javaFxTutorialPart3.md>
[fx4]: <tutorials/javaFxTutorialPart4.md>

# Feedback, Bug Reports

* If you have feedback or bug reports, please post in [se-edu/duke issue tracker](https://github.com/se-edu/duke/issues).
* We welcome pull requests too.


#Acknowledgements
## Acknowledgements
Credit to Jeffry Lum for the fxml and java files for JAVAFX GUI -  [JavaFx TutorialPart 4](https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md).  

Acknowledgements FastReader.java was adapted from https://www.javatips.net/api/AlgoDS-master/src/timus/FastReader.java. However, I tweaked some components to cater to my own needs.

Thanos Image for GUI is from https://www.theverge.com/2018/4/16/17243794/avengers-infinity-wars-thanos-origin-story-marvel

Tony Stark Image is from https://pngimage.net/tony-stark-png-5/

Edith Image is from https://www.amazon.com/Glasses-Sticker-Trucks-Laptop-NOK163/dp/B07VS9NPPL

Solution for closing GUI window is adapted from https://stackoverflow.com/questions/52393982/javafx-problem-with-platform-runlater-delayed-rendering-of-canvas-graphic