/**
 * deals with interactions with the user
 */
public class Ui {

    public Ui() {

    }

    public String greeting() {
        return "Hello! I'm Duke\n What can i do for you?";
    }

    public String view(String input) {
        return "These are all your tasks for " + input + ":";
    }

    public String date(String input) {
        return "These are all your tasks on " + input + ":";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String gotIt() {
        return "Got it. I've added this task:";
    }

    public String remove(Task t) {
        return "Noted. I've removed this task:\n " + t;
    }

    public String storeSize(int num) {

        return "Now you have " + num + " tasks in the list.";
    }

    public String doneTask() {

        return "Nice! I've marked this task as done: ";
    }

    public String taskList() {

        return "Here are the tasks in your list: ";
    }

    public String matchingTask() {
        return "Here are the matching tasks in your list:";
    }
}
