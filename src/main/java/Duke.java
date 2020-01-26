import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum Command {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, DEFAULT
    }
    static Scanner scanner = new Scanner(System.in);
    static String buffer;
    static String input;
    static String[] tokens;
    static ArrayList<Task> list;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        boolean done = false;
        list = new ArrayList<>();
        String param = "";
        String[] params;
        Command command;
        int index;
        Task t;
        while (true) {
            try {
                input = scanner.nextLine();
                tokens = input.split(" ", 2);
                command = Command.valueOf(tokens[0].toUpperCase());
                if (tokens.length > 1) {
                    param = tokens[1];
                } else {
                    param = "";
                }
                switch (command) {
                    case TODO:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        }
                        t = new ToDo(param);
                        addTask(t);
                        break;
                    case DEADLINE:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /by ")) {
                            throw new MissingDeadlineParamException();
                        }
                        params = param.split(" /by ");
                        t = new Deadline(params[0], params[1]);
                        addTask(t);
                        break;
                    case EVENT:
                        if (param.equals("")) {
                            throw new MissingDescriptionException();
                        } else if (!param.contains(" /at ")) {
                            throw new MissingEventParamException();
                        }
                        params = param.split(" /at ");
                        t = new Event(params[0], params[1]);
                        addTask(t);
                        break;
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        done = true;
                        break;
                    case LIST:
                        if (list.size() == 0) {
                            System.out.println("You have no tasks in your list.");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println((i + 1) + "." + list.get(i));
                            }
                        }
                        break;
                    case DONE:
                        try {
                            index = Integer.parseInt(tokens[1]);
                            list.get(index - 1).markAsDone();
                        } catch (NumberFormatException ex) {
                            throw new InvalidArgumentException();
                        } catch (IndexOutOfBoundsException ex) {
                            throw new InvalidArgumentException();
                        }
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index - 1));
                        break;
                    case DELETE:
                        try {
                            index = Integer.parseInt(tokens[1]);
                            t = list.remove(index - 1);
                        } catch (NumberFormatException ex) {
                            throw new InvalidArgumentException();
                        } catch (IndexOutOfBoundsException ex) {
                            throw new InvalidArgumentException();
                        }
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                if (done)
                    break;
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }

    }

    private static void addTask(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
    }

}
