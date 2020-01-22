import main.java.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        System.out.println("-------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList<>();

        while(true) {
            String x = sc.nextLine();
            if (x.equals("bye")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
                sc.close();
                System.exit(0);
            } else if (x.equals("list")) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Tasks.size(); i++) {
                    System.out.println(i+1 + ". " + Tasks.get(i).getStatusIcon() + Tasks.get(i).getDescription());
                }
                System.out.println();
                System.out.println("-------------------------------------------------------------");
            } else if (x.contains("done")) {
                int index = Integer.valueOf(x.split(" ",2)[1]);
                Tasks.get(index-1).markAsDone();
            }
            else {
                System.out.println("-------------------------------------------------------------");
                Task t = new Task(x);
                Tasks.add(t);
                System.out.println("added: " + t.getDescription());
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
            }
        }

    }
}
