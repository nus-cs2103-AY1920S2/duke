import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static void checkArr(String task, boolean hasDescription, String[] elements) throws DukeException {
        if (elements.length == 1) {
            throw new MissingInfoException(task, hasDescription);
        }
    }

    public static Command parse(String input) throws DukeException {
        Command c;
        String[] inputElements = input.split(" ", 2);
        String command = inputElements[0];
        try {
            if (command.equals("bye")) {
                c = new ExitCommand();
            } else if (command.equals("list")) {
                c = new ListCommand();
            } else if (command.equals("todo")) {
                Parser.checkArr(command, false, inputElements);
                c = new AddCommand(new ToDo(inputElements[1]));
            } else if (command.equals("deadline")) {
                Parser.checkArr(command, false, inputElements);
                String[] descElements = inputElements[1].split(" /by ", 2);
                Parser.checkArr(command, true, descElements);
                LocalDate date = LocalDate.parse(descElements[1]);
                c = new AddCommand(new Deadline(descElements[0], date));
            } else if (command.equals("event")) {
                Parser.checkArr(command, false, inputElements);
                String[] descElements = inputElements[1].split(" /at ", 2);
                Parser.checkArr(command, true, descElements);
                LocalDate date = LocalDate.parse(descElements[1]);
                c = new AddCommand(new Event(descElements[0], date));
            } else if (command.equals("delete")) {
                Parser.checkArr(command, false, inputElements);
                int i = Integer.parseInt(inputElements[1]);
                c = new DeleteCommand(i - 1);
            } else if (command.equals("done")) {
                Parser.checkArr(command, false, inputElements);
                int i = Integer.parseInt(inputElements[1]);
                c = new DoneCommand(i - 1);
            } else {
                c = new UnknownCommand();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
        return c;
    }
}
