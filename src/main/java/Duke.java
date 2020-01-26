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

        String exitKey = "bye";
        String viewListKey = "list";
        String finishKey = "(done)(\\s+)(\\d+)";
        Pattern finishPattern = Pattern.compile(finishKey);

        String exitGreeting = line + "Bye. Hope to see you again soon!\n" + line;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(greeting);

            while (true) {
                String input = br.readLine();
                Matcher finishMatcher = finishPattern.matcher(input);

                if (exitKey.equals(input)) {
                    System.out.println(exitGreeting);
                    System.exit(0);
                } else if (viewListKey.equals(input)) {
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
                } else if (finishMatcher.find()) {
                    Integer finishedTaskIndex = Integer.parseInt(finishMatcher.group(3)) - 1;
                    Task finishedTask = taskArray[finishedTaskIndex];
                    finishedTask.markAsDone();

                    System.out.println(line + taskCompleteCongrats + " " + finishedTask.getStatus() + "\n" + line);
                }
                else {
                    Task currentTask = new Task(input);
                    taskArray[nextInsertPin] = currentTask;
                    nextInsertPin ++;
                    System.out.println(line + "added: " + input + "\n" + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
