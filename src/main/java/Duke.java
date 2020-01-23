import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingText = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greetingText);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<String>();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                String byeText = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                System.out.println(byeText);
                break;
            } else if (input.equals("list")){
                printList(taskList);
            } else {
                addList(input, taskList);
            }
        }
    }

    public static void addList(String task, ArrayList<String> list) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + task + "\n" +
                "    ____________________________________________________________");
        list.add(task);
    }

    public static void printList(ArrayList<String> list) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i+1) + ". " + list.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static String wrapHorizontalLines(String input) {
        return "    ____________________________________________________________\n" +
                "     " + input + "\n" +
                "    ____________________________________________________________";
    }
}
