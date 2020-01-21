import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static Scanner sc;
    static ArrayList<Task> tasks;

    public static void readCommand(Command command) throws DukeDescriptionException {
        switch (command) {
            case LIST:
                System.out.println("Here are all your tasks:");
                for (Task task: tasks) {
                    System.out.println(task.getId() + "." + task);
                }
                break;
            case DONE:
                int id = sc.nextInt();
                tasks.get(id - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done: \n" +
                        "  " + tasks.get(id - 1));
                break;
            case TODO:
                String todo = sc.nextLine();
                if (todo.isEmpty()) throw new DukeDescriptionException("Empty Description");
                Task taskToDo = new Todo(tasks.size() + 1, todo);
                tasks.add(taskToDo);
                System.out.println("I've added this task: \n" +
                        "  " + taskToDo + "\n Now you have " +
                        tasks.size() + " tasks in the list." );
                break;
            case EVENT:
                String event = sc.nextLine();
                if (event.isEmpty()) throw new DukeDescriptionException("Empty Description");
                int eventDate = event.indexOf("/");
                Task taskEvent = new Event(tasks.size() + 1, event.substring(0, eventDate),
                        event.substring(eventDate + 4));
                tasks.add(taskEvent);
                System.out.println("I've added this task: \n" +
                        "  " + taskEvent + "\n Now you have " +
                        tasks.size() + " tasks in the list." );
                break;
            case DEADLINE:
                String deadline = sc.nextLine();
                if (deadline.isEmpty()) throw new DukeDescriptionException("Empty Description");
                int dLineDate = deadline.indexOf("/");
                Task taskDLine = new Deadline(tasks.size() + 1, deadline.substring(0, dLineDate),
                        deadline.substring(dLineDate + 4));
                tasks.add(taskDLine);
                System.out.println("I've added this task: \n" +
                        "  " + taskDLine + "\n Now you have " +
                        tasks.size() + " tasks in the list." );
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreak = "_____________________________" +
                "_______________________________";
        sc = new Scanner(System.in);
        tasks = new ArrayList<>();

        System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);

        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) break;
            else {
                try {
                    System.out.println(lineBreak);
                    Command command = Command.lookUp(next);
                    readCommand(command);
                } catch (InvalidCommandException e) {
                    System.out.println("Sorry I do not know what that means!");
                } catch (DukeDescriptionException e) {
                    System.out.println("OOPS! You forgot to include a description!");
                } finally {
                    System.out.println(lineBreak);
                }
            }
        }
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
