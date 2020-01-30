package duke.main;

import duke.exceptions.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    static Scanner scanner = new Scanner(System.in);

    public static void showLoadingError() {
        System.out.println("Error loading duke.main.Storage module. Your tasks may not be loaded or saved.");
    }

    public static void showDukeError(DukeException e) {
        System.out.println(e);
    }

    public static void start() {
        System.out.println("Hello! I'm duke.main.Duke\nWhat can I do for you?");

    }

    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String getInput() {
        return scanner.nextLine();
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void print(String line) {
        System.out.println(line);
    }

    public static void print(List<String> lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public static void taskAdded(Task t, TaskList taskList) {
        print("Got it. I've added this task:\n" + t);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    public static void taskMarkedAsDone(Task t) {
        print("Nice! I've marked this task as done:");
        print(t);
    }

    public static void taskDeleted(Task t, TaskList taskList) {
        print("Noted. I've removed this task:");
        print(t);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    public static void tasksCleared() {
        print("All tasks have been cleared");
    }

    public static void calendarNoItems() {
        print("No matching events/deadlines found.");
    }

    public static void calendarDisplayItems(LocalDate calendarDate, List<String> lines) {
        print("Here are the events/deadlines in your list on " +
                calendarDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ":");
        print(lines);
    }

    public static void printNoTasks() {
        print("You have no tasks in your list.");
    }

    public static void printTasks(List<String> lines) {
        print("Here are the tasks in your list:");
        print(lines);
    }

}
