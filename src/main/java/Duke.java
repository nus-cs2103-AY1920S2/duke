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

import main.java.NoDescriptionException;

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
    static String deleteTaskStart = "Noted. I have removed this task:\n";


    static String exitKey = "bye";
    static String viewListKey = "list";
    static String deleteKey = "(delete)(\\s+)(\\d+)";
    static String finishKey = "(done)(\\s+)(\\d+)";
    static String todoKey = "(todo)(.*)";
    static String deadlineKey = "(deadline)(.*)(\\/by)(.*)";
    static String eventKey = "(event)(.*)(\\/at)(.*)";

    static Pattern deletePattern = Pattern.compile(deleteKey);
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

        ArrayList<Task> taskList = new ArrayList<Task>();
        int nextInsertPin = 0;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(greeting);

            while (true) {
                String input = br.readLine();
                StringBuilder addTaskEnd = new StringBuilder("Now you have  tasks in the list.\n");

                Matcher deleteMatcher = deletePattern.matcher(input);
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
                    for (int i = 0; i < taskList.size(); i++) {
                        if (taskList.get(i) == null) {
                            continue;
                        }
                        listOverView = listOverView + Integer.toString(i + 1) + "."
                                + taskList.get(i).toString() + "\n";
                    }
                    listOverView = listOverView + line;
                    System.out.println(listOverView);
                }
                else if (deleteMatcher.find()) {
                    int deletedTaskIndex = Integer.parseInt(deleteMatcher.group(3)) - 1;
                    Task deletedTask = taskList.get(deletedTaskIndex);
                    taskList.remove(deletedTaskIndex);
                    System.out.println(line + deleteTaskStart + " " + deletedTask.toString() + "\n" +
                            "Now you have " + Integer.toString(taskList.size()) + " tasks in the list.\n" + line);
                }
                else if (finishMatcher.find()) {
                    int finishedTaskIndex = Integer.parseInt(finishMatcher.group(3)) - 1;
                    Task finishedTask = taskList.get(finishedTaskIndex);
                    finishedTask.markAsDone();

                    System.out.println(line + taskCompleteCongrats + " " + finishedTask.toString() + "\n" + line);
                }
                else if (todoMatcher.find()){
                    String description = todoMatcher.group(2).trim();
                    if ("".equals(description)) {
                        System.out.println(line + "OOPS!!! The description of a todo cannot be empty.\n" + line);
                    } else {
                        ToDoTask currentTask = new ToDoTask(description);
                        taskList.add(currentTask);

                        String addTaskEndStr = addTaskEnd.insert(13, taskList.size()).toString();
                        System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                                addTaskEndStr + line);
                    }
                }
                else if (deadlineMatcher.find()){
                    String description = deadlineMatcher.group(2).trim();
                    String by = deadlineMatcher.group(4).trim();
                    DeadLineTask currentTask = new DeadLineTask(description, by);
                    taskList.add(currentTask);

                    String addTaskEndStr = addTaskEnd.insert(13, taskList.size()).toString();
                    System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                            addTaskEndStr + line);
                }
                else if (eventMatcher.find()){
                    String description = eventMatcher.group(2).trim();
                    String at = eventMatcher.group(4).trim();
                    EventTask currentTask = new EventTask(description, at);
                    taskList.add(currentTask);

                    String addTaskEndStr = addTaskEnd.insert(13, taskList.size()).toString();
                    System.out.println(line + addTaskStart + currentTask.toString() + "\n" +
                            addTaskEndStr + line);

                }
                else {
                    System.out.println(line + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
