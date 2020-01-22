import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;

public class Duke {
    public static void main(String[] args) {
        //Greeting message
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printBye();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.contains("done")) {
                String[] inputArr = input.split(" ");
                int num = Integer.parseInt(inputArr[1]);
                doneAction(list, num);
            } else {
                addToList(input, list);
            }
        }
    }

    public static void addToList(String input, ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
        Task task = new Task(input);
        list.add(task);
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (Task s : list) {
            int listNo = list.indexOf(s) + 1;
            System.out.println("    " + listNo + "." + s);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void doneAction(ArrayList<Task> list, int num) {
        Task doneTask = list.get(num - 1);
        doneTask.mark();
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as completed:");
        System.out.println("      " + doneTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
