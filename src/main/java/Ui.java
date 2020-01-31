import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public static void printHello() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        String greetingText = "    ____________________________________________________________\n" +
                logo +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greetingText);
    }

    public static void printBye() {
        String byeText = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        System.out.println(byeText);
    }

    public static void printList(TaskList taskList) {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (Task s : taskList.arrList) {
            System.out.println("     " + (taskList.arrList.indexOf(s)+1) + ". " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printException(Exception e) {
        System.out.println(e);
    }

    public static void printDelete(Task task, ArrayList<Task> arrList) {
        System.out.println("   ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + arrList.size() +" tasks in the list.");
    }
    public static void printDone(Task task, ArrayList<Task> arrList) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + task + "\n" +
                "    ____________________________________________________________");
    }

    public static void printAdd(Task task, ArrayList<Task> arrList) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + arrList.size() + " tasks in the list.");
    }

}
