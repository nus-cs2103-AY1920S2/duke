package handler;

import entity.*;
import entity.task.Task;
import parser.CommandParser;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void startUI() {



        String command = scanner.nextLine();
        CommandParser commandParser = new CommandParser(this);
        while (!commandParser.terminateUI(command)) {

        }
        System.out.println("____________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________");
    }
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye!");
    }

    public void showLine() {
        System.out.println("____________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void confirmAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void confirmDeleteTask(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void confirmDoneTask(int index, String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(index + 1 + "." + task);
    }

    public void invalidFormatError() {
        System.out.println("Invalid format for input!");
    }

    public void errorSavingChanges() {
        System.out.println("Error saving changes!");
    }

    public void listAllTasks(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).printTask());
        }
    }

    public void listDueTasks(List<Task> tasks, Date date) {
        System.out.println("Here is a list of events and deadlines: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).printTask());
        }
    }

    public void printCustomMessage(String message) {
        System.out.println(message);
    }

    public void missingDetails() {
        System.out.println("☹ OOPS!!! Information about the task is missing");
    }
}
