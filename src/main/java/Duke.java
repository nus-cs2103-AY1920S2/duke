import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<Task> store = new ArrayList<>();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] check = command.split(" ");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + store.get(i));
                }
            } else if (check[0].equals("done")){
                System.out.println("Nice! I've marked this task as done:");
                store.get(Integer.parseInt(check[1]) - 1).markAsDone();
                System.out.println(store.get(Integer.parseInt(check[1]) - 1));
            } else {
                if (check[0].equals("todo")) {
                    String tasking = "";
                    for(int i = 1; i < check.length; i++) {
                        tasking += check[i];
                        tasking += " ";
                    }
                    tasking = tasking.trim();
                    ToDo todo = new ToDo(tasking);
                            store.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                } else if (check[0].equals("deadline")) {
                    String[] newCheck = Arrays.copyOfRange(check, 1, check.length);
                    int by = Arrays.asList(newCheck).indexOf("/by");

                    String tasking = "";
                    for(int i = 0; i < by; i++) {
                        tasking += newCheck[i];
                        tasking += " ";
                    }
                    tasking = tasking.trim();

                    String dL = "";
                    for(int i = by + 1; i < newCheck.length; i++) {
                        dL += newCheck[i];
                        dL += " ";
                    }
                    dL = dL.trim();

                    Deadline deadline = new Deadline(tasking, dL);
                    store.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");                } else if(check[0].equals("event")) {
                    String[] newCheck = Arrays.copyOfRange(check, 1, check.length);
                    int by = Arrays.asList(newCheck).indexOf("/at");

                    String tasking = "";
                    for(int i = 0; i < by; i++) {
                        tasking += newCheck[i];
                        tasking += " ";
                    }
                    tasking = tasking.trim();

                    String time = "";
                    for(int i = by + 1; i < newCheck.length; i++) {
                        time += newCheck[i];
                        time += " ";
                    }
                    time = time.trim();
                    Event event = new Event(tasking, time);
                    store.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                } else {
                    System.out.println("Command not available.");

                }


            }
        }

    }
}
