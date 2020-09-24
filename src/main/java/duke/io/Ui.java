package duke.io;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TasksList;
import java.util.Scanner;

public class Ui {
    // INPUT ///////////////////////////////////////////////////////////////////////////////////
    public String getInput() {
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        input = input.trim();

        return input;
    }

    // returns an array where the very first word (command) is put into index 0 and everything else (arguments) in index 1
    public String[] separateCommandAndArguments(String input) {
        return input.split(" ", 2);
    }

    // OUTPUT //////////////////////////////////////////////////////////////////////////////////
    public void handleException(DukeException exception) {
        print("Exception!" + System.lineSeparator() +
                "\t" + exception.description);
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void printLineSeparator() {
        print("---------------------------------------");
    }

    public void printNumOfTasks(TasksList tasksList) {
        int numOfTasks = tasksList.size();
        print("Now you have " + numOfTasks + " task(s) in the list.");
    }

    public void printHomeScreen() {
        printLineSeparator();

        print("Hello! I'm Duke" + System.lineSeparator() +
                "What can I do for you?");

        printLineSeparator();
    }

    public void printExitScreen() {
        print("Bye. Hope to see you again soon!");

        printLineSeparator();
    }

    public void printSuccessfulAddEntry(TasksList tasksList, Task newTask) {
        printLineSeparator();

        print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask);
        printNumOfTasks(tasksList);

        printLineSeparator();
    }

    public void printSuccessfulRemoveEntry(TasksList tasksList, Task deletedTask) {
        printLineSeparator();

        print("Got it. I've deleted this task:" + System.lineSeparator() +
                "\t" + deletedTask);
        printNumOfTasks(tasksList);

        printLineSeparator();
    }

    public void printDataLoadSuccess() {
        printLineSeparator();
        print("Successfully loaded Data!");
        printLineSeparator();
    }

    public void printList(TasksList tasksList) {
        int entryNum = 1;

        printLineSeparator();

        for(Task task : tasksList.tasks) {
            if(task == null) {
                break;
            }

            print(entryNum + "." + task);
            entryNum++;
        }

        printLineSeparator();
    }
}
