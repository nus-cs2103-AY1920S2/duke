# User Guide

## Features and Usage:

**Compile and run:**

To generate the jar file:

cd to this repository and run 

```
./gradlew shadowJar
```

To run the generated jar file:

```
java -jar duke-0.1.3.jar
```

**Test:**

You should use "/" to divide your command, task name and date, if needed.

**Add tasks:**

1. add todo task to task list:

   ```
   todo/task name
   ```

   for example:

   ```
   todo/finish my IP task
   ```

2. add event:

   add an event to task list:

   ```
   event/event name/date
   ```

   for example:

   ```
   event/finish my IP task/March 1st 2020
   ```

3. add task with deadline

   add an task with deadline to task list:

   deadline/task name/date

   for example:

   ```
   deadline/finish my IP task/March 1st 2020
   ```

4. delete task with its name

   delete a task

   ```
   delete/task name
   ```

   for example:

   ```
   delete/finish my IP task
   ```

**Save tasks:**

1. save tasks to disk:

   ```
   save
   ```

**Show tasks:**

1. print all tasks in the chat box:

   ```
   list
   ```

Find task:

1. Find a task with its name:

   ```
   find/task name
   ```

   for example:

   ```
   find/finish my IP task
   ```

**Mark tasks:**

1. mark task as done:

   ```
   done/task name
   ```

   for example:

   ```
   done/finish my IP task
   ```

2. mark task as undone:

   ```
   undone/task name
   ```

   for example:

   ```
   undone/finish my IP task
   ```

   