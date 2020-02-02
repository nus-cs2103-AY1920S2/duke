import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static void handle(TaskList taskList, Ui ui) throws DukeException {
        Scanner scanner = new Scanner(System.in);
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        boolean loop = true;
        try {
            String input = scanner.nextLine();
            String[] inputSplit = prepareHandle(input, exceptionHandler);

            switch (inputSplit[0]) {
                case "todo":
                    taskList.addTodo(prepareTodo(inputSplit[1]));
                    break;
                case "deadline":
                    taskList.addDeadline(inputSplit[1]);
                    break;
                case "event":
                    taskList.addEvent(inputSplit[1]);
                    break;
                case "done":
                    taskList.markDone(prepareDoneDelete(inputSplit[1]));
                    break;
                case "delete":
                    taskList.deleteTask(prepareDoneDelete(inputSplit[1]));
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
            } catch(DukeException ex) {
            System.err.println(ex);
        }

        while (loop);
    }

    public static String[] prepareHandle(String input, ExceptionHandler exceptionHandler) throws DukeException {
        String[] inputSplit = input.split(" ", 2);
        exceptionHandler.checkWrongCommand(inputSplit[0]);
        exceptionHandler.checkEmptyField(inputSplit, inputSplit[0]);

        return inputSplit;
    }

    public static String prepareTodo(String input) {
        return input;
    }

    public static String[] prepareDeadlineEvent(String input) {
        return input.split("/", 2);
    }

    public static int prepareDoneDelete(String input) {
        return Integer.parseInt(input);
    }
}

