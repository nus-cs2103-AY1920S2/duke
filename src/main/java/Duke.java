package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.css.Match;
import main.java.Task;
import main.java.ToDoTask;
import main.java.EventTask;
import main.java.DeadLineTask;

public class Duke {
    public static void main(String[] args) {
        BufferedReader br = null;

        Task[] taskArray = new Task[200];
        int nextInsertPin = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________________\n";

        String greeting = line +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line;

        String taskCompleteCongrats = "Nice! I've marked this task as done:\n";
        String exitGreeting = line + "Bye. Hope to see you again soon!\n" + line;

        String exitKey = "bye";
        String viewListKey = "list";
        String finishKey = "(done)(\\s+)(\\d+)";
        String todoKey = "(todo)(.*)";
        String deadlineKey = "(deadline)(.*)(\\/by)(.*)";
        String eventKey = "(event)(.*)(\\/at)(.*)";

        Pattern finishPattern = Pattern.compile(finishKey);
        Pattern todoPattern = Pattern.compile(todoKey);
        Pattern deadlinePattern = Pattern.compile(deadlineKey);
        Pattern eventPattern = Pattern.compile(eventKey);

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(greeting);

            while (true) {
                String input = br.readLine();
                Matcher finishMatcher = finishPattern.matcher(input);
                Matcher todoMatcher = todoPattern.matcher(input);
                Matcher deadlineMatcher = deadlinePattern.matcher(input);
                Matcher eventMatcher = eventPattern.matcher(input);

                if (exitKey.equals(input)) {
                    System.out.println(exitGreeting);
                    System.exit(0);
                }
                else if (viewListKey.equals(input)) {
                    String listOverView = line;
                    for (int i = 0; i < taskArray.length; i++) {
                        if (taskArray[i] == null) {
                            break;
                        }
                        listOverView = listOverView + Integer.toString(i + 1) + ".[" + taskArray[i].getStatusIcon()
                                + "]" + taskArray[i].getDescription() + "\n";
                    }
                    listOverView = listOverView + line;
                    System.out.println(listOverView);
                }
                else if (finishMatcher.find()) {
                    Integer finishedTaskIndex = Integer.parseInt(finishMatcher.group(3)) - 1;
                    Task finishedTask = taskArray[finishedTaskIndex];
                    finishedTask.markAsDone();

                    System.out.println(line + taskCompleteCongrats + " " + finishedTask.toString() + "\n" + line);
                }
                else if (todoMatcher.find()){
                    String description = todoMatcher.group(2).trim();
                    ToDoTask currentTask = new ToDoTask(description);
                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + "added: " + currentTask.toString() + "\n" + line);
                }
                else if (deadlineMatcher.find()){
                    String description = deadlineMatcher.group(2).trim();
                    String by = deadlineMatcher.group(4).trim();
                    DeadLineTask currentTask = new DeadLineTask(description, by);
                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + "added: " + currentTask.toString() + "\n" + line);
                }
                else if (eventMatcher.find()){
                    String description = eventMatcher.group(2).trim();
                    String at = eventMatcher.group(4).trim();
                    EventTask currentTask = new EventTask(description, at);
                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + "added: " + currentTask.toString() + "\n" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
