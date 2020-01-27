import java.util.ArrayList;

public class Ui {
    private int maxLength;

    public Ui() {
        this.maxLength = 60;
    }

    public void showWelcome() {
        String logo = " _________                        \n" +
                " __  ____/_______________________ \n" +
                " _  / __ _  __ \\  __ \\_  ___/  _ \\  >(o )___\n" +
                " / /_/ / / /_/ / /_/ /(__  )/  __/   ( ._> /\n" +
                " \\____/  \\____/\\____//____/ \\___/     `---'";

        System.out.println("Hello it's\n" + logo);

        String greeting = "Honk, this Goose. Do you need help with something?";
        System.out.println("\n" + wrapLine(greeting));
    }

    public void showBye() {
        System.out.println(wrapLine("Honk honk!"));
    }

    public void showList(String taskList) {
        System.out.println(wrapLine("Honk! Here's your task list: \n" + taskList));
    }

    public void showAddTask(Task addedTask, ArrayList<Task> taskList) {
        String addHeader = " Honk! Okay added the task:\n  ";
        System.out.println(wrapLine(addHeader + addedTask + showCount(taskList)));
    }

    public void showDoneTask(Task doneTask) {
        String doneHeader = "Good job! I've honked it as done:\n  ";
        System.out.println(wrapLine(doneHeader + doneTask));
    }

    public void showDeleteTask(Task deletedTask, ArrayList<Task> taskList) {
        String deleteHeader = "Honk! Removed this task from the list:\n  ";
        System.out.println(wrapLine(deleteHeader + deletedTask + showCount(taskList)));
    }

    private String showCount(ArrayList<Task> taskList) {
        String count = taskList.size() == 1
                ? "\n\n Now you have " + taskList.size() + " task in the list."
                : "\n\n Now you have " + taskList.size() + " tasks in the list.";
        return count;
    }

    // Exceptions
    public void showLoadingError() {
        System.out.println(wrapLine("Honk! Loading error!"));
    }

    public void showIOException() {
        System.out.println(wrapLine("Honk! Something went wrong."));
    }

    public void showError(String msg) {
        System.out.println(wrapLine(msg));
    }

    // Formatting
    public String wrapLine(String msg) {
        String top = "";
        for (int i = 0; i < maxLength; i++) {
            top += "_";
        }
        top += "\n";

        String bottom = "__  ";
        for (int i = 0; i < maxLength - 4; i++) {
            bottom += "_";
        }
        String bottomArrow = "\n   /\n";

        return top + "\n" + " " + msg + "\n" + bottom + bottomArrow;
    }
}