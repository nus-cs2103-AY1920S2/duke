import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String divider = "____________________________________________________________";
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?\n" + divider);

        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //System.out.println(LocalDateTime.parse("2020-12-13 1300", dtf).format(DateTimeFormatter.ofPattern("yyyy")));
        int i = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext() /*!nextLine.equals("bye")*/) {
            String nextLine = sc.nextLine();
            try {
                validate(nextLine);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Please try again!");
                System.out.println(divider);
                continue;
            }

            // action: DELETE
            if(nextLine.contains("delete")) {
                String[] substrings = nextLine.split(" ");
                System.out.println("Successfully deleted the following task:\n"
                        + tasks.get(Integer.parseInt(substrings[1])-1));
                tasks.remove(Integer.parseInt(substrings[1])-1);
                i--;
                System.out.println("You have " + i + " tasks in your list currently.");
                System.out.println(divider);
                continue;
            }

            // to check if 'list' service is called
            if (nextLine.equals("list")) {
                System.out.println("Here are the current tasks in your list:");
                int listStart = 1;
                for (Task task : tasks) {
                    System.out.println(listStart + ". " + task);
                    listStart++;
                }
                System.out.println(divider);
                continue;
            } else if (nextLine.contains("bye")) {
                break;
            }

            // if the action is done
            if (nextLine.contains("done")) {
                String[] substrings = nextLine.split(" ");
                int taskNum = Integer.parseInt(substrings[1]);
                tasks.get(taskNum - 1).taskDone();
                System.out.println("Alright! You have successfully completed:");
                System.out.println(tasks.get(taskNum - 1));
                System.out.println(divider);
                continue;
            }

            // different Task additions
            if (nextLine.contains("todo")) {
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Todo(substrings[1]));
            } else if (nextLine.contains("event")) { // event creation
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Event(substrings[1].split(" /at")[0],
                        LocalDate.parse(substrings[1].split("/at ")[1], dtf)));
            } else if (nextLine.contains("deadline")) {            // deadline creation
                String[] substrings = nextLine.split(" ",2);
                tasks.add(new Deadline(substrings[1].split(" /by")[0],
                        LocalDate.parse(substrings[1].split("/by ")[1], dtf)));
            }
            System.out.println("Successfully added:\n" + tasks.get(i).toString());
            i++;
            System.out.println("You now have " + i + " number of tasks in the list");
            System.out.println(divider);
        }
        System.out.println("Hope my service has been of great help! See you again!");
    }

    public static void validate(String s) throws DukeException {
        String action = s.split(" ")[0];
        if (!action.equals("list") && !action.equals("done") && !action.equals("todo")
                && !action.equals("event") && !action.equals("deadline") && !action.equals("bye")
                && !action.equals("delete")) {
            throw new DukeException("This is not a valid action you may take sir.");
        } else if ((s.contains("todo") || s.contains("event") || s.contains("deadline")
                || s.contains("done") || s.contains("delete")) && s.split(" ").length == 1) {
            throw new DukeException("Your description may not be empty");
        }
    }
}


