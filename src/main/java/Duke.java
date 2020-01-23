import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?\n" + divider);

        Task[] tasks = new Task[100];
        //String[] tasks = new String[100];
        int i = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() /*!nextLine.equals("bye")*/) {
            // to check if 'list' service is called
            String nextLine = sc.nextLine();
            try {
                validate(nextLine);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please try again!");
                continue;
            }
            if (nextLine.equals("list")) {
                System.out.println("Here are the current tasks in your list:");
                int listStart = 1;
                for (Task task : tasks) {
                    if (task == null) {
                        System.out.println(divider);
                        break;
                    }
                    System.out.println(listStart + ". " + task);
                    listStart++;
                }
                continue;
            } else if (nextLine.contains("bye")) {
                break;
            }

            // if the action is done
            if (nextLine.contains("done")) {
                String[] substrings = nextLine.split(" ");
                int taskNum = Integer.parseInt(substrings[1]);
                tasks[taskNum - 1].taskDone();
                System.out.println("Alright! You have successfully completed:");
                System.out.println(tasks[taskNum - 1]);
                System.out.println(divider);
                continue;
            }

            // normal addition of task
            // to do creation
            if (nextLine.contains("todo")) {
                String[] substrings = nextLine.split(" ",2);
                tasks[i] = new Todo(substrings[1]);
            } else if (nextLine.contains("event")) { // event creation
                String[] substrings = nextLine.split(" ",2);
                tasks[i] = new Event(substrings[1].split(" /at")[0], substrings[1].split("/at ")[1]);
            } else if (nextLine.contains("deadline")) {            // deadline creation
                String[] substrings = nextLine.split(" ",2);
                tasks[i] = new Deadline(substrings[1].split(" /by")[0], substrings[1].split("/by ")[1]);
            }
            System.out.println("Successfully added:\n" + tasks[i].toString());
            i++;
            System.out.println("You now have " + i + " number of tasks in the list");
            System.out.println(divider);
        }
        System.out.println("Hope my service has been of great help! See you again!");
    }

    public static void validate(String s) throws DukeException {
        if (!s.contains("list") && !s.contains("done") && !s.contains("todo")
                && !s.contains("event") && !s.contains("deadline") && !s.contains("bye")) {
            throw new DukeException("This is not a valid action you may take sir.");
        } else if ((s.contains("todo") || s.contains("event") || s.contains("deadline") || s.contains("done")) && s.split(" ").length == 1) {
            throw new DukeException("Your description may not be empty");
        }
    }
}


