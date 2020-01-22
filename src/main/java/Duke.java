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
            if(command.trim().toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            try {
                commandHandler(command, store);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void commandHandler (String command, ArrayList<Task> store) throws DukeException {
        String[] check = command.split(" ");

        switch (check[0].toLowerCase()) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + store.get(i));
                }
                break;
            case "done":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a done cannot be empty.");
                }
                int intOfInterest;
                try {
                    intOfInterest = Integer.parseInt(check[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS! Please use an Integer together with the done command");
                }
                if(intOfInterest >= store.size()) {
                    throw new DukeException("OOPS! The Integer you used is invalid. Index of Array is out of bounds.");
                }
                System.out.println("Nice! I've marked this task as done:");
                store.get(Integer.parseInt(check[1]) - 1).markAsDone();
                System.out.println(store.get(Integer.parseInt(check[1]) - 1));
                break;
            case "todo":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a todo cannot be empty.");
                }
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
                break;
            case "deadline":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of a deadline cannot be empty.");
                }
                String[] newCheck = Arrays.copyOfRange(check, 1, check.length);
                int by = Arrays.asList(newCheck).indexOf("/by");
                if(by == -1) {
                    throw new DukeException("OOPS! Please remember to insert an '/by' in the deadline description.");
                }
                String taskingD = "";
                for(int i = 0; i < by; i++) {
                    taskingD += newCheck[i];
                    taskingD += " ";
                }
                taskingD = taskingD.trim();

                String dL = "";
                for(int i = by + 1; i < newCheck.length; i++) {
                    dL += newCheck[i];
                    dL += " ";
                }
                dL = dL.trim();

                Deadline deadline = new Deadline(taskingD, dL);
                store.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                break;
            case "event":
                if(check.length == 1) {
                    throw new DukeException("OOPS! The description of an event cannot be empty.");
                }
                String[] newCheck2 = Arrays.copyOfRange(check, 1, check.length);
                int at = Arrays.asList(newCheck2).indexOf("/at");
                if(at == -1) {
                    throw new DukeException("OOPS! Please remember to insert an '/at' in the event description.");
                }

                String taskingE = "";
                for(int i = 0; i < at; i++) {
                    taskingE += newCheck2[i];
                    taskingE += " ";
                }
                taskingE = taskingE.trim();

                String time = "";
                for(int i = at + 1; i < newCheck2.length; i++) {
                    time += newCheck2[i];
                    time += " ";
                }
                time = time.trim();
                Event event = new Event(taskingE, time);
                store.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                break;
            default:
                throw new DukeException("OOPS! I'm sorry but I don't know what that means :-(");
        }
    }
}
