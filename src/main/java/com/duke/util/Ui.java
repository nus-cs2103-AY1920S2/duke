package com.duke.util;

import com.duke.task.Task;
import com.duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private String space = "     ";
    private String line = space + "_____________________________________________";
    Scanner scanner = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println(space + "OOPS! There is a problem loading save file");
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + "\n" + space + "What can I do for you?");

    }

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String msg) {
        System.out.println(space + msg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println(space + "Bye. Hope to see you again soon!");
    }

    public void showList(TaskList tasks) {
        String output = space + "Here are the tasks in your list: ";
        int noOfTasks = tasks.tasks.size();
        for (int i = 0; i < noOfTasks; i++) {
            int index = i+1;
            output += "\n" + space + index + ". " + tasks.tasks.get(i);
        }
        System.out.println(output);
    }

    public void showDone(Task t) {
        String output = space + "Nice! I've marked this task as done: "
                + "\n" + space + t;
        System.out.println(output);
    }

    public void showDelete(Task t, int count) {
        String output = space + "Noted. I've removed this task: "
                + "\n" + space + "  " + t + "\n" + space + "Now you have " + count +
                " tasks in your list.";
        System.out.println(output);
    }

    public void showAdd(Task t, int count) {
        String out = space + "Got it. I've added this task: " + "\n" + space
                + "  " + t + "\n" + space + "Now you have " + count +
                " tasks in your list.";
        System.out.println(out);
    }
}
