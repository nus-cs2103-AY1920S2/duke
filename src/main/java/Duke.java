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

    protected String user_name;
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String line = "____________________________________________________________\n";

    static String greeting = line +
            "Hello ! I'm Duke\n" +
            "What can I do for you?\n" + line;
    static String viewListGreeting = "Here are the tasks in your list:\n";
    static String taskCompleteCongrats = "Nice! I've marked this task as done:\n";
    static String exitGreeting = line + "Bye. Hope to see you again soon!\n" + line;
    static String addTaskStart = "Got it. I've added this task:\n";


    static String exitKey = "bye";
    static String viewListKey = "list";
    static String finishKey = "(done)(\\s+)(\\d+)";
    static String todoKey = "(todo)(.*)";
    static String deadlineKey = "(deadline)(.*)(\\/by)(.*)";
    static String eventKey = "(event)(.*)(\\/at)(.*)";

    static Pattern finishPattern = Pattern.compile(finishKey);
    static Pattern todoPattern = Pattern.compile(todoKey);
    static Pattern deadlinePattern = Pattern.compile(deadlineKey);
    static Pattern eventPattern = Pattern.compile(eventKey);

    public Duke (String user_name) {
        this.user_name = user_name;
    }

    private static boolean isExitKey(String input) {
        return exitKey.equals(input);
    }

    public static void main(String[] args) {
        BufferedReader br = null;

        Task[] taskArray = new Task[200];
        int nextInsertPin = 0;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(greeting);

            while (true) {
                String input = br.readLine();
                StringBuilder addTaskEnd = new StringBuilder("Now you have  tasks in the list.\n");

                Matcher finishMatcher = finishPattern.matcher(input);
                Matcher todoMatcher = todoPattern.matcher(input);
                Matcher deadlineMatcher = deadlinePattern.matcher(input);
                Matcher eventMatcher = eventPattern.matcher(input);

                if (exitKey.equals(input)) {
                    System.out.println(exitGreeting);
                    System.exit(0);
                }
                else if (viewListKey.equals(input)) {
                    String listOverView = line + viewListGreeting;
                    for (int i = 0; i < taskArray.length; i++) {
                        if (taskArray[i] == null) {
                            break;
                        }
                        listOverView = listOverView + Integer.toString(i + 1) + "."
                                + taskArray[i].toString() + "\n";
                    }
                    listOverView = listOverView + line;
                    System.out.println(listOverView);
                }
                else if (finishMatcher.find()) {
                    int finishedTaskIndex = Integer.parseInt(finishMatcher.group(3)) - 1;
                    Task finishedTask = taskArray[finishedTaskIndex];
                    finishedTask.markAsDone();

                    System.out.println(line + taskCompleteCongrats + " " + finishedTask.toString() + "\n" + line);
                }
                else if (todoMatcher.find()){
                    String description = todoMatcher.group(2).trim();
                    ToDoTask currentTask = new ToDoTask(description);
                    String addTaskEndStr = addTaskEnd.insert(13, nextInsertPin + 1).toString();
                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                            addTaskEndStr + line);
                }
                else if (deadlineMatcher.find()){
                    String description = deadlineMatcher.group(2).trim();
                    String by = deadlineMatcher.group(4).trim();
                    String addTaskEndStr = addTaskEnd.insert(13, nextInsertPin + 1).toString();
                    DeadLineTask currentTask = new DeadLineTask(description, by);

                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                            addTaskEndStr + line);
                }
                else if (eventMatcher.find()){
                    String description = eventMatcher.group(2).trim();
                    String at = eventMatcher.group(4).trim();
                    String addTaskEndStr = addTaskEnd.insert(13, nextInsertPin + 1).toString();
                    EventTask currentTask = new EventTask(description, at);

                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                            addTaskEndStr + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
