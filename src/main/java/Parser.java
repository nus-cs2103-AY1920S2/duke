import java.util.Scanner;

public class Parser {
    public static void handle(TaskList taskList, Ui ui) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do {
            try {
                String input = scanner.nextLine();
                String commandType = extractCommandType(input);
                String[] inputSplit = input.split(" ", 2);

                switch (commandType) {
                    case "todo":
                        taskList.addTodo(prepareTodo(inputSplit));
                        break;
                    case "deadline":
                        taskList.addDeadline(prepareDeadline(inputSplit));
                        break;
                    case "event":
                        taskList.addEvent(inputSplit);
                        break;
                    case "done":
                        taskList.markDone(prepareDoneDelete(inputSplit));
                        break;
                    case "delete":
                        taskList.deleteTask(prepareDoneDelete(inputSplit));
                        break;
                    case "list":
                        ui.listTasks(taskList);
                        break;
                    case "bye":
                        ui.exitDuke();
                        loop = false;
                        break;
                    default:
                        //ui.print("Uhh... You're gonna have to say that again, Red.");
                        //change to exception handler
                        break;
                }
            } catch (InvalidCommandException ex) {
                System.out.println(ex);
            }
        } while (loop);
    }

    public static String extractCommandType(String input) throws InvalidCommandException{
        String[] inputSplit = input.split(" ", 2);
        boolean isValidCommand = checkValidCommand(inputSplit[0]);

        if (!isValidCommand) {
            throw new InvalidCommandException("");
        }

        return inputSplit[0];
    }

    public static String prepareTodo(String[] input) throws InvalidTodoException {
        if (!checkValidTodo(input)) {
            throw new InvalidTodoException("");
        } else {
            return input[1];
        }
    }

    public static String[] prepareDeadline(String[] input) throws InvalidDeadlineException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!checkValidDeadline(fieldDetails)) {
            throw new InvalidDeadlineException("");
        } else {
            return fieldDetails;
        }
    }

    public static String[] prepareEvent(String[] input) throws InvalidEventException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!checkValidEvent(fieldDetails)) {
            throw new InvalidEventException("");
        } else {
            return fieldDetails;
        }
    }

    public static int prepareDoneDelete(String input) {
        return Integer.parseInt(input);
    }

    public static boolean checkValidCommand(String type) {
        if (type.equals("todo")
                || type.equals("deadline")
                || type.equals("event")
                || type.equals("list")
                || type.equals("done")
                || type.equals("delete")
                || type.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkValidTodo(String[] input) {
        if (input.length == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkValidDeadline(String[] input) {
        if (input.length == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkValidEvent(String[] input) {
        if (input.length == 3) {
            return true;
        } else {
            return false;
        }
    }
}

