import java.util.ArrayList;

public class Ui {

    public void showWelcome() {
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");
    }

    public void showLoadingError() {
        System.out.println("Task list not found! Creating one now...");
    }

    public void showError(Exception e) {
       System.out.println(e.toString());
    }

    public void showExitLine() {
        System.out.println("Okay then! Goodbye!");
    }

    public void printTaskMarkedDone(Task task) {
        System.out.println("Okay noted! You have completed the below task:\n" + task);
    }

    public void printTodoTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeadlineTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printEventTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printList(ArrayList<Task> taskList) {
        System.out.println("The below is what you have told me so far. Have you completed them?");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void printRemainingList(Task taskDeleted, ArrayList<Task> taskList) {
        System.out.println("Okay noted! I have deleted the below task:\n" + taskDeleted);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
