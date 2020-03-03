# Usage

**To Run the Application**

1. Type './gradlew build'
1. In Intellij,right click on 'main/java/duke/Launcher.java' and select Run.


# What commands can I enter?

List | sample user command
---------------|---------------
`list <category>` | list expenses

Note: you can view what to list by simply typing 'list'

For todos | sample user command
---------------|---------------
`todo <description of task>` | todo borrow book

For events | sample user command
---------------|---------------
`event <description of task> /at <datetime>` | event project meeting /at 2020-02-12
`...` | event project meeting /at mon 6pm

For deadline | sample user command
---------------|---------------
`deadline <description of task> /by <datetime>` | event return book /by 2020-02-12
`...` | event project meeting /by mon 6pm

To set an alias | sample user command
---------------|---------------
`set alias <command to link> <alias>` | set alias todo t

Make a spending | sample user command
---------------|---------------
`spend <category> 15` | spend food 15

* Category only accepts food, books and transport

* Note that specifiers **/by** and **/at** are not interchangeable

  e.g. events have to use **/at**
  deadlines have to use **/by**

  
  
  
  
Create Jar without Shadow 
  
Steps to export your project with javafx
1. Add this to your build.gradle

```

jar {
    exclude 'META-INF/*.SF', 'META-INF/*.DSA', 'META-INF/*.RSA', 'META-INF/*.MF'

    manifest {
        attributes 'Main-Class': 'duke.Launcher',
                'Class-Path': configurations.runtime.files.collect { "build/libs/$it.name" }.join(' ')
    }
}
```

Run these commands in a terminal (java -jar has to be run)
In the root project, 
1.    ./gradlew build 
1.   ./gradlew jar
1.   run java -jar duke-xxx.jar
you will receive noclassdeferr


1. Open eclipse
1. Import the project by General > projects from file system
1. Setup javafx as an external library 
Eclipse > Preferences > Build Path > Add user library external jars (using the jars in build/distributions/duke-xx.zip )
1. Edit run configuration by right clicking Run As > Edit Run Configuration. Update the project and you can find the main class when you click search
1. Add the user library to your project's build path. Right Click project > build path > Add libraries > User library
Add the javafx11 library u just defined to the project under classpath

Almost done
1. Right click and select export > Java > Java runnable jar
1. Select 'Package dependencies' and a location to save the jar file

Your jar file should now work on cross-platform
