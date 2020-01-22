import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> list = new ArrayList<>();
    private static String horizontalLine = "__________________________________________";

    public static void main(String[] args) throws DukeException {
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am \n" + logo + "\n" + "What can I do for you?");

        while (scan.hasNextLine()) {
            try {
                String command = scan.nextLine();
                String[] arrOfCommands = command.split(" ");
                Task task = new Task(command);

                if (command.equals("bye")) {
                    System.out.println(horizontalLine
                            + "\n"
                            + "Bye-bye! See you again, my friend!"
                            + "\n"
                            + horizontalLine);
                    break;
                } else if (command.equals("list")) {
                    System.out.println("list" + "\n" + horizontalLine + "\n" + "Here are your tasks!");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(horizontalLine);

                } else if (arrOfCommands[0].equals("done")) {
                    int num = Integer.parseInt(arrOfCommands[1]);
                    Task doneTask = list.get(num - 1);
                    System.out.println(horizontalLine + "\n" + "Fantastic! This task is a done-deal!" + "\n");
                    doneTask.markAsDone();
                    System.out.println(doneTask + "\n" + horizontalLine);

                } else if (command.startsWith("deadline")) {
                    String[] commands = command.split("/by");
                    Deadline deadline = new Deadline(commands[0], commands[1]);
                    list.add(deadline);
                    System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                    System.out.println(deadline + "\n");
                    System.out.println("You currently have " + list.size() + " task(s) in the list.");
                    System.out.println(horizontalLine);

                } else if (command.startsWith("todo")) {
                    String[] commands = command.split("todo ");
                    try {
                        if (commands.length >= 2) {
                            ToDo toDo = new ToDo(commands[1]);
                            list.add(toDo);
                            System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                            System.out.println(toDo + "\n");
                            System.out.println("You currently have " + list.size() + " task(s) in the list.");
                            System.out.println(horizontalLine);
                        } else {
                            throw new DukeException("");
                        }
                    } catch (DukeException e) {
                        System.err.println(horizontalLine);
                        System.err.println("There is no valid input after to do. Please try again.");
                        System.err.println(horizontalLine);
                    }
                } else if (command.startsWith("event")) {
                    String commandWithoutEvent = command.substring(6);
                    String[] commands = commandWithoutEvent.split("/at");
                    Event event = new Event(commands[0], commands[1]);
                    list.add(event);
                    System.out.println(horizontalLine + "\n" + "Alright, I've added this task:" + "\n");
                    System.out.println(event + "\n");
                    System.out.println("You currently have " + list.size() + " task(s) in the list.");
                    System.out.println(horizontalLine);
                } else {
                    throw new DukeException("");
                }
            } catch (DukeException e) {
                System.err.println(horizontalLine);
                System.err.println("That was not a valid input. Please try again.");
                System.err.println(horizontalLine);
            }
        }
    }
}
