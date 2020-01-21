import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        input();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void input() {
        Scanner sc;
        String statement;
        ArrayList<Task> arrList = new ArrayList<>();

        sc = new Scanner(System.in);
        statement = sc.nextLine();

        while (!statement.equals("bye")) {
            if (statement.equals("list") && arrList.size() != 0) {
                System.out.println("These are the tasks in your list:");
                for (int i=1; i <= arrList.size(); i++) {
                    Task t = arrList.get(i-1);
                    System.out.println(i + ". [" +t.getStatusIcon()+ "] " +t.description);
                }
                System.out.println();
            }
            else if (statement.equals("list")) {
                System.out.println("Empty");
                System.out.println();
            }
            else if ((statement.split(" ")[0]).equals("done")) {
                int a = Integer.parseInt(statement.split(" ")[1])-1;
                arrList.get(a).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [" +arrList.get(a).getStatusIcon()+ "] " +arrList.get(a).description);
                System.out.println();
            }
            else {
                Task t = new Task(statement);
                arrList.add(t);
                System.out.println("added: " + statement);
                System.out.println();
            }
            sc = new Scanner(System.in);
            statement = sc.nextLine();
        }
    }
}