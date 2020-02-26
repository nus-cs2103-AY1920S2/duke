## Project Duke user manual

## Supported features
| Features      |   Description |
| ------------  |   ----------- |
| todo          |   Creates a new TODO task     |
| event         |   Creates a new EVENT task    |
| deadline      |   Creates a new DEADLINE task |
| mark          |   Checks the completion marker on the task    |
| unmark        |   Removes the completion marker on the task    |
| delete        |   Deletes an existing task    |
| save          |   Saves the list of tasks to file    |
| quit          |   exit    |

## Building & executing the jar
Lets start by compiling java classes

```java
./gradlew build
```

This creates a file `duke-<MAJOR>-<MINOR>-<PATH>.jar` under the *build/libs* directory

Execute the code using

```java
java -jar <jar_class>.jar com.nus.duke
```

## Walkthrough
Sample walkthrough diagram can be found [here](Ui.png)

Lets start by creating a new task. 

Create a `todo` task
```java
todo brush my teeth
```

Create an `event` task. We can specify the event timing via the /by token
```java
event return library book /at 10pm
```

Create an `deadline` task. We can specify the deadline timing via the /at token
```java
deadline finish CS2103 online lecture /by 20-02-2020 09:00
```

List all the tasks you have created
```java
list
```

Once we have brushed our teeth, we can mark the task as completed
```java
mark brush my teeth
```

We can search for tasks related to the key work `return` via
```java
find return
```

Save the file. The files will be saved to the current working directory under the name _duke-saved-record.txt_
```java
save
```

You can then exit the program
```java
exit
```