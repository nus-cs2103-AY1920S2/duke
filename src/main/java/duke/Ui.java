package duke;

import duke.task.Task;

import java.util.Scanner;

class Ui {
    private static final int HORIZONTAL_LINE_LENGTH = 76;

    String getLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }

    String sayHello() {
        return("Hello, I'm Duke!\nWhat can I do for you?");
    }

    String sayBye() {
        return "Goodbye. Hope to see you again soon!";
    }

    String getInput(Scanner sc) {
        return sc.nextLine();
    }

    String outputTask(Task task, int numberOfTasks) {
        String status;
        if (numberOfTasks == 1) {
            status = "There is now 1 task in the list.";
        } else {
            status = "There are now " + numberOfTasks + " tasks in the list.";
        }
        return String.format("Got it. I've added this task:\n  %s\n%s", task, status);
    }

    String outputException(DukeException e) {
        return e.getMessage();
    }

    String format(String message) {
        String horizontalLine = new String(new char[HORIZONTAL_LINE_LENGTH]).replace("\0", "-");
        message = horizontalLine + "\n" + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }

    void print(String message) {
        System.out.println(message);
    }
}
