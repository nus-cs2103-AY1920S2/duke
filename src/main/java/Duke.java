import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class Duke {
    /**
     * main method for Duke.
     */
    static Scanner scan = new Scanner(System.in);
    static List<Task> storedTasks = new ArrayList<>();

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am \n" + logo + "your personal buddy. What's up?\n" +
                            "____________________________________________________________\n");

        while(true) {
            String input = scan.nextLine(); //reads in user input
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                    case "bye":
                        divider("Aww okay, see you next time!");
                        return;
                    case "list":
                        if (storedTasks.size() < 1) {
                            divider("no tasks");
                            break;
                        }
                        divider("");
                        System.out.println("Here are the tasks in your list:");
                        int index = 1;
                        String output = "";
                        for (Task t : storedTasks) {
                            output = index + ". " + t.toString();
                            System.out.println(output);
                            index++;
                        }
                        divider("");
                        break;
                    case "done":
                        if (command.length <= 1) {throw new DukeException("OOPS boi. Need moar arguments!!");}
                        try {Integer.parseInt(command[1]);} catch(Exception e) {throw new DukeException("Input a number boi.");}
                        if (Integer.parseInt(command[1]) > storedTasks.size()) {
                            throw new DukeException("Boi. You don't have that many tasks boi! :)");}

                        int taskNum = Integer.parseInt(command[1]);
                        storedTasks.get(taskNum - 1).setDone();
                        divider("");
                        System.out.println("Nice! I've marked this task as done:\n" +
                                storedTasks.get(taskNum - 1).toString());
                        divider("");
                        break;

                    case "todo":
                        if (command.length <= 1) {throw new DukeException("OOPS boi. Need moar arguments!!");}
                        Task task = new Todo(input.substring(5)); //stores input to storedText List
                        insertTask(task);
                        break;

                    case "deadline": {
                        String[] elements = input.split("/by");
                        if (elements.length <= 1) {
                            throw new DukeException("OOPS boi. Need moar arguments!!");
                        }
                        String deadlineDescription = elements[0].substring(9);
                        String by = elements[1].substring(1);
                        Task deadline = new Deadline(deadlineDescription, by);
                        insertTask(deadline);
                        break;
                    }

                    case "event": {
                        String[] elements = input.split("/at");
                        if (elements.length <= 1) {
                            throw new DukeException("OOPS boi. Need moar arguments!!");
                        }
                        String eventDescription = elements[0].substring(6);
                        String at = elements[1].substring(1);
                        Task event = new Event(eventDescription, at);
                        insertTask(event);
                        break;
                    }

                    default:
                        throw new DukeException("unknown command!!!");
//                    storedTasks.add(new Task(input)); //stores input to storedText List
//                    divider("added " + input);
                }
            } catch(DukeException d) {
                divider(d.getMessage());
            } catch(Exception e) {
                divider("General error caught boi: " + e.toString());
            }
        }
    }

    private static void divider(String s) {
        if (s.length() > 0) {
            System.out.println("____________________________________________________________\n"
                    + s + "\n"
                    + "____________________________________________________________\n");
            return;
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void insertTask(Task task) {
        storedTasks.add(task);
        divider("Got it. I've added this task:\n" +
                task.toString()+"\n" +
                "Now you have " + storedTasks.size() + " tasks in the list.");
    }
}
