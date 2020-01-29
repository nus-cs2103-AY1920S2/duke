import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        //create list
        List<Task> list = new ArrayList<Task>();

        while (true) {
            String command = sc.nextLine();
            String arr[] = command.split(" ", 2);
            String firstWord = arr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                if (!list.isEmpty()) {
                    for (int i=0; i<list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i+1) + "." + t.toString());
                    }
                }
            } else if (firstWord.equals("done")){
                //which task done
                String secNum = arr[1];
                //set that task to done
                Task t = list.get(Integer.parseInt(secNum) - 1);
                t.setDone();
                //print output
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
            } else if (firstWord.equals("todo")) {
                //add task to list
                ToDos t = new ToDos(arr[1]);
                list.add(t);
                //print output
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("deadline")) {
                //separate task and deadline
                String arr2[] = arr[1].split("/", 2);
                String arr3[] = arr2[1].split(" ", 2);
                //add task and deadline to list
                Deadline d = new Deadline(arr2[0], arr3[1]);
                list.add(d);
                //print output
                System.out.println("Got it. I've added this task:");
                System.out.println(d.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("event")) {
                //separate task and time
                String arr2[] = arr[1].split("/", 2);
                String arr3[] = arr2[1].split(" ", 2);
                //add task and deadline to list
                Events e = new Events(arr2[0], arr3[1]);
                list.add(e);
                //print output
                System.out.println("Got it. I've added this task:");
                System.out.println(e.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
        }
    }
}
