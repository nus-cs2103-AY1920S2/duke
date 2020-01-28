import java.util.Scanner;

public class Ui {

    public Ui(){};

    public void showLine(){
        System.out.println("______________________________________________________________" );
    }

    public void displayIntro() {
        String hi = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(hi);
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayError(String error) {
        System.out.println(error);
    }

    public void displaySaveError() {
        System.out.println("File not saved.");
    }

    public void displayLoadError() {
        System.out.println("File not found.");
    }

    public void displayDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void displayAddedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.");
    }

    public void displayDeletedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        System.out.println("Noted. I've removed this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.");
    }

    public void displayTasks(TaskList taskList) {
        System.out.println("Here are your remaining tasks:");
        for (Task t: taskList.tasks) {
            int index = taskList.tasks.indexOf(t) + 1;
            System.out.println("" + index + "." + t.toString());
        }
    }

    public void displayBye() {
        System.out.println("Bye nerd. Hope to see you again soon!");
    }
}