package duke;

import java.util.ArrayList;

public class Ui {

    Ui(){
    }

    void showLoadingError(DukeException e) {
        System.out.println(e);
    }

    void greetUser() {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
    }

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

    static void printTask(Task newTask, int size) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    static void printDone(Task updatedTask) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + updatedTask);
        System.out.println(line);
    }

    static void printBye() {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    static void printRemove(Task removedTask, int size) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + removedTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    static void printException(DukeException e) {
        String line = "   ____________________________________________________________";
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }
}
