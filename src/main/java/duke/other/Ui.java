package duke.other;

import duke.task.*;
import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
    }

    public void welcome() {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";
        printWithBorder(greeting);
    }

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public void showError(String errMessage) {
            System.out.println(errMessage);
    }

    public void showLoadingError() {
        System.out.println("    No task list found! Creating new task list...");
    }

    public static void taskAdded(Task task, TaskList taskList) {
        System.out.println("    Alright! Task added:\n      " + task.toString() + "\n    Now you have " + taskList.getSize()
                + " task(s) in your list!");
    }

    public static void deadlineInputError() throws DukeException {
        throw new DukeException("    Invalid date and/or time format! \n" +
                "    Specify deadline with: /at <YYYY/MM/DD> <HH:MM>\n" +
                "    i.e. deadline Project Meeting /by 2020/01/28 18:00");
    }

    public static void eventInputError() throws DukeException {
        throw new DukeException("    Invalid date and/or time format! \n" +
                "    Specify event with: /at <YYYY/MM/DD> <HH:MM>\n" +
                "    i.e. event Project Meeting /at 2020/01/28 18:00");
    }

    public static void todoInputError() {
        throw new DukeException("    Specify your todo \n" + "    i.e. todo Complete tutorials ");
    }

    public static void dateInputError() throws DukeException {
        throw new DukeException("    Please enter a valid date in <YYYY/M/D> format\n" +
                "    i.e. 2020/10/28");
    }

    public static void doneInputError() throws DukeException{
        throw new DukeException("    Task doesn't exist!");
    }

    public static void deleteInputError() throws DukeException {
        throw new DukeException("    Task doesn't exist! Add a new task!");
    }


    public static void showLine() {
        System.out.println("    ###################################################");
    }

    public static void printWithBorder(String message) {
        System.out.println("    ###################################################\n"
                + "    " + message + "\n"
                + "    ###################################################\n");
    }
}

