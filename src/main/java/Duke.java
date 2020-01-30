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
    protected ArrayList<Task> taskList;

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String line = "____________________________________________________________\n";

    static String greeting = "I'm Duke.\n What can I do for you?\n";
    static String viewListGreeting = "Here are the tasks in your list:\n";
    static String taskCompleteCongrats = "Nice! I've marked this task as done:\n";
    static String exitGreeting = "Bye. Hope to see you again soon!\n";
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

    public Duke () {
        this.user_name = "";
        this.taskList = new ArrayList<Task>();
    }

    public Duke (String user_name) {
        this.user_name = user_name;
        this.taskList = new ArrayList<Task>();
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    private static boolean isExitKey(String input) { return exitKey.equals(input); }

    private static boolean isViewListKey(String input) {return viewListKey.equals(input); }

    private static boolean isDeleteKey(String input) {
        Matcher deleteMatcher = deletePattern.matcher(input);
        return deleteMatcher.find();
    }

    private static boolean isFinishKey(String input) {
        Matcher finishMatcher = finishPattern.matcher(input);
        return finishMatcher.find();
    }

    private static boolean isTodoKey(String input) {
        Matcher todoMatcher = todoPattern.matcher(input);
        return todoMatcher.find();
    }

    private static boolean isDeadLineKey(String input) {
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        return deadlineMatcher.find();
    }

    private static boolean isEventKey(String input) {
        Matcher eventMatcher = eventPattern.matcher(input);
        return eventMatcher.find();
    }

    private int findIndex(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return Integer.parseInt(matcher.group(3)) - 1;
    }

    private String findDescription(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(2).trim();
    }

    private String findEndTime(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(4).trim();
    }

    private void appendList(Task task) {
        this.taskList.add(task);
        StringBuilder addTaskEnd = new StringBuilder("Now you have  tasks in the list.\n");

        String addTaskEndStr = addTaskEnd.insert(13, this.taskList.size()).toString();
        System.out.println(line + addTaskStart + task.toString() + "\n" +
                addTaskEndStr + line);
    }

    private void deleteElementFromList(Integer position) {
        Task deletedTask = this.taskList.get(position);
        taskList.remove(position-1);
        System.out.println(line + deleteTaskStart + " " + deletedTask.toString() + "\n" +
                "Now you have " + Integer.toString(this.taskList.size()) + " tasks in the list.\n" + line);
    }

    public void greet() {
        System.out.println(line + "Hello "+ this.user_name + "! " + greeting + line);
    }

    private void exit() {
        System.out.println(line + exitGreeting + line);
        System.exit(0);
    }

    public void listen(String input) {
        if (this.isExitKey(input)) {
            this.exit();
        }
        else if (this.isViewListKey(input)) {
            String listOverView = line + viewListGreeting;
            for (int i = 0; i < this.taskList.size(); i++) {
                if (this.taskList.get(i) == null) {
                    continue;
                }
                listOverView = listOverView + Integer.toString(i + 1) + "."
                        + taskList.get(i).toString() + "\n";
            }
            listOverView = listOverView + line;
            System.out.println(listOverView);
        }
        else if (this.isDeleteKey(input)) {
            int deletedTaskIndex = this.findIndex(deletePattern, input);
            this.deleteElementFromList(deletedTaskIndex);
        }
        else if (this.isFinishKey(input)) {
            int finishedTaskIndex = this.findIndex(finishPattern, input);
            Task finishedTask = this.taskList.get(finishedTaskIndex);
            finishedTask.markAsDone();

            System.out.println(line + taskCompleteCongrats + " " + finishedTask.toString() + "\n" + line);
        }
        else if (this.isTodoKey(input)){
            String description = this.findDescription(todoPattern, input);
            if ("".equals(description)) {
                System.out.println(line + "OOPS!!! The description of a todo cannot be empty.\n" + line);
            } else {
                ToDoTask currentTask = new ToDoTask(description);
                this.appendList(currentTask);
            }
        }
        else if (this.isDeadLineKey(input)){
            String description = this.findDescription(deadlinePattern, input);
            String by = this.findEndTime(deadlinePattern, input);
            DeadLineTask currentTask = new DeadLineTask(description, by);
            this.appendList(currentTask);
        }
        else if (this.isEventKey(input)){
            String description = this.findDescription(eventPattern, input);
            String at = this.findEndTime(eventPattern, input);
            EventTask currentTask = new EventTask(description, at);
            this.appendList(currentTask);

        }
        else {
            System.out.println(line + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        Duke myDuke = new Duke();

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(line + "Please input user name.\n" + line);
            String input = br.readLine();
            myDuke.setUserName(input.trim());
            myDuke.greet();

            while (true) {
                String next_input = br.readLine();
                myDuke.listen(next_input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
