import java.util.Scanner;

public class TaskHandler {
    public void handle(Boxer boxer) {
        Scanner scanner = new Scanner(System.in);
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        boolean loop = true;
        do {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);

            try {
                exceptionHandler.checkWrongCommand(inputSplit[0]);
                exceptionHandler.checkEmptyField(inputSplit, inputSplit[0]);

                switch (inputSplit[0]) {
                    case "todo":
                        boxer.todo(inputSplit[1]);
                        break;
                    case "deadline":
                        boxer.deadline(inputSplit[1]);
                        break;
                    case "event":
                        boxer.event(inputSplit[1]);
                        break;
                    case "done":
                        int index;
                        index = Integer.parseInt(inputSplit[1]);
                        boxer.done(index);
                        break;
                    case "delete":
                        index = Integer.parseInt(inputSplit[1]);
                        boxer.delete(index);
                        break;
                    case "list":
                        boxer.list();
                        break;
                    case "bye":
                        boxer.exit();
                        loop = false;
                        break;
                    default:
                        Boxer.print("Uhh... You're gonna have to say that again, Red.");
                        break;
                }
            } catch(DukeException ex) {
                System.err.println(ex);
            }
        } while (loop);
    }
}
