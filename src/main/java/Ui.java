import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    public void showLoadingError() {
        System.out.println("Error loading Storage module. Your tasks may not be loaded or saved.");
    }
    public void showDukeError(DukeException e) {
        System.err.println(e);
    }
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

    }
    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public String getInput() {
        return scanner.nextLine();
    }
    public void print(Object o) {
        System.out.println(o);
    }
    public void print(String line) {
        System.out.println(line);
    }
    public void print(List<String> lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
    public void taskAdded(Task t, TaskList taskList) {
        print("Got it. I've added this task:\n" + t);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }
    public void taskMarkedAsDone(Task t) {
        print("Nice! I've marked this task as done:");
        print(t);
    }
    public void taskDeleted(Task t, TaskList taskList) {
        print("Noted. I've removed this task:");
        print(t);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }
    public void tasksCleared() {
        print("All tasks have been cleared");
    }
    public void calendarNoItems() {
        print("No matching events/deadlines found.");
    }
    public void calendarDisplayItems(LocalDate calendarDate, List<String> lines) {
        print("Here are the events/deadlines in your list on " + calendarDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ":");
        print(lines);
    }
    public void printNoTasks() {
        print("You have no tasks in your list.");
    }
    public void printTasks(List<String> lines) {
        print("Here are the tasks in your list:");
        print(lines);
    }

}
