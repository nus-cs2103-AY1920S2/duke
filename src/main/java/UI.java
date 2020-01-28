import java.util.ArrayList;

public class UI {

    public UI() {
    }

    static String horizontalLine = "***********************************************";

    static void printIntro() {
        System.out.println(horizontalLine);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, I'm Duke \n"
                + "How can I help you today?");
        System.out.println(horizontalLine);
    }

    public static void printBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void printList() {
        ArrayList<Task> list = TaskList.getTaskList();
        System.out.println(horizontalLine);
        if (list.isEmpty()) {
            System.out.println("You have 0 outstanding tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
        System.out.println(horizontalLine);
    }

    public static void wrapper(String string) {
        String output = horizontalLine + "\n" + string + "\n" + horizontalLine;
        System.out.println(output);
    }
}
