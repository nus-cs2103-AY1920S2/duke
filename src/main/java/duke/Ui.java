package duke;

import duke.task.Task;

import java.util.Scanner;

class Ui {
    String getInput(Scanner sc) {
        return sc.nextLine();
    }

    String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return(logo + "\nHello, I'm Duke!\nWhat can I do for you?");
    }

    String exit() {
        return "Goodbye. Hope to see you again soon!";
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

    String outputException(Exception e) {
        return e.getMessage();
    }

    String format(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-");
        message = horizontalLine + "\n" + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
