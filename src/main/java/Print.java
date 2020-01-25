import java.util.ArrayList;

// Class which contains all the print methods required for
// The different printing functions.


public class Print {

    private static String lines = "        ____________________________________________________________";
    private static String space = "        ";

    // For the print formatting for to-do, deadline and event
    static void print(Task task, ArrayList<Task> list) {
        System.out.println(lines);
        got_it_line();
        System.out.println(space + task);
        System.out.println(space + " Now you have " + (list.size()) + " tasks in the list.");
        System.out.println(lines);
    }

    // For print formatting when user types "Bye"
    static void print_bye() {
        System.out.println(lines);
        System.out.println("        Bye. Hope to see you again soon");
        System.out.println(lines);
    }

    // For print formatting when user types "Delete"
    static void print_delete(Task deleted_task, int list_size) {
        System.out.println(lines);
        System.out.println(space + "Noted. I've removed this task:");
        System.out.println(space + deleted_task);
        System.out.println(space + "Now you have " + list_size + " tasks in the list.");
        System.out.println(lines);
    }

    static void got_it_line() {
        System.out.println(space + " Got it. I've added this task: ");
    }

}