package duke;

/**
 * This class takes care of the user interface
 * especially printing outputs to the user.
 **/
public class Ui {

    /**
     * Constructor of the UI object.
     **/
    Ui(){
    }

    /**
     * Printing an error of loading a file to the user.
     **/
    void showLoadingError(DukeException e) {
        System.out.println(e);
    }

    /**
     * Printing an greeting message to the user.
     **/
    void greetUser() {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
    }

    /**
     * Printing the task lists of the user.
     **/
    static void printList(TaskList tasks) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHere are the tasks in your list:");
        int i = 1;
        for (Task s : tasks.getTasks()) {
            System.out.println("\t" + i + "." + s.toString());
            i++;
        }
        System.out.println(line);
    }

    /**
     * Printing confirmation of task adding to the user.
     **/
    static void printTask(Task newTask, int size) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Printing confirmation of changing task done status to done.
     * to the user
     **/
    static void printDone(Task updatedTask) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + updatedTask);
        System.out.println(line);
    }

    /**
     * Printing a farewell message to the user.
     **/
    static void printBye() {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Printing confirmation of removing task from the list.
     * to the user
     **/
    static void printRemove(Task removedTask, int size) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + removedTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Printing an exception message to the user.
     **/
    static void printException(DukeException e) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }

    /**
     * Printing the tasks that has related keywords.
     **/
    static void printFindings(TaskList foundTasks) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHere are the matching tasks in your list:");
        int i = 1;
        for (Task s : foundTasks.getTasks()) {
            System.out.println("\t" + i + "." + s.toString());
            i++;
        }
        System.out.println(line);
    }
}
