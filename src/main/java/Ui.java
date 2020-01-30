public class Ui {

    public Ui() {

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\n  " +  "What can i do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void gotIt() {
        System.out.println("Got it. I've added this task:");
    }

    public void remove(Task t) {
        System.out.println("Noted. I've removed this task:\n " + t );
    }

    public void storeSize(int num) {
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public void doneTask() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public void taskList() {
        System.out.println("Here are the tasks in your list: ");
    }

}
