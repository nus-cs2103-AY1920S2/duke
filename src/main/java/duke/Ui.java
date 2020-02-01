package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scan;
    private String userName;

    public Ui() {
        scan = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("A new save file has been initialised for you.");
    }

    public void showError(DukeException e) {
        System.out.println(e.toString());
    }

    public String printWelcomeMessageAndGetName() {
        System.out.println("Hello there! I am Duke, your personal assistant. May I know your name?");
        this.userName = scan.nextLine();
        return userName;
    }

    public void printFarewellMessage() {
        System.out.println("\t" + "Adios. It was my pleasure assisting you. Keep smiling " + userName + ".");
    }

    public String getInstruction() {
        System.out.println("How may I help you " + userName + "?");
        return scan.nextLine();
    }

    public void printList(TaskList taskList) {
        System.out.println(taskList);
    }

    public void printDoneMessage(Task completed) {
        System.out.println("\t" + "Hooray! You've finally managed to finish this task:");
        System.out.println("\t\t" + completed);
    }

    public void printDeleteMessage(Task deleted) {
        System.out.println("\t" + "Got it! I've removed this task:");
        System.out.println("\t\t" + deleted);
    }

    public void printWhatsupMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have nothing assigned on that day.");
        } else {
            System.out.println("\t" + "The tasks you have on that day are:");
            for (Task task: tasks) {
                System.out.println("\t\t" + task);
            }
        }
    }

    public void printAddTaskMessage(Task newTask) {
        String identifier;
        if (newTask instanceof Todo) {
            identifier = "to do";
        } else if (newTask instanceof Deadline) {
            identifier = "deadline";
        } else {
            identifier = "event";
        }
        System.out.println("\t" + "Awesome! I've added this " + identifier + " :");
        System.out.println("\t\t" + newTask);
    }

    public void printFindMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\t" + "There were no tasks matching what you said.");
        } else {
            System.out.println("\t" + "Here you go, this is probably what you were finding:");
            for (Task task: tasks) {
                System.out.println("\t\t" + task);
            }
        }
    }
}