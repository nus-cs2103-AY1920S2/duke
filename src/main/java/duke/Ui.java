package duke;

import duke.task.Task;

import java.util.Scanner;

class Ui {
    private final Scanner sc;

    Ui(Scanner sc) {
        this.sc = sc;
    }

    String getInput() {
        return sc.nextLine();
    }

    void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        print("Hello, I'm Duke!\nWhat can I do for you?");
    }

    void exit() {
        print("Goodbye. Hope to see you again soon!");
    }

    void print(String message) {
        System.out.println(style(message));
    }

    void printTask(Task task, int numberOfTasks) {
        String status;
        if (numberOfTasks == 1) {
            status = "There is now 1 task in the list.";
        } else {
            status = "There are now " + numberOfTasks + " tasks in the list.";
        }
        print(String.format("Got it. I've added this task:\n  %s\n%s", task, status));
    }

    void printException(Exception e) {
        print(e.getMessage());
    }

    private String style(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-");
        message = horizontalLine + "\n" + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
