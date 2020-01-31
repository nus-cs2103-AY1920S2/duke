import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private static void checkArr(String task, boolean hasDescription, String arr[]) throws DukeException {
        if (arr.length == 1) {
            throw new MissingInfoException(task, hasDescription);
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        Command c;
        String cmdInstructionArr[] = fullCommand.split(" ", 2);
        String command = cmdInstructionArr[0];
        try {
            if (command.equals("bye")) {
                c = new ExitCommand();
            } else if (command.equals("list")) {
                c = new ListCommand();
            } else if (command.equals("todo")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                c = new AddCommand(new ToDo(cmdInstructionArr[1]));
            } else if (command.equals("deadline")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                String descTimeArr[] = cmdInstructionArr[1].split(" /by ", 2);
                Parser.checkArr(command, true, descTimeArr);
                LocalDate date = LocalDate.parse(descTimeArr[1]);
                c = new AddCommand(new Deadline(descTimeArr[0], date));
            } else if (command.equals("event")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                String descTimeArr[] = cmdInstructionArr[1].split(" /at ", 2);
                Parser.checkArr(command, true, descTimeArr);
                LocalDate date = LocalDate.parse(descTimeArr[1]);
                c = new AddCommand(new Event(descTimeArr[0], date));
            } else if (command.equals("delete")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                int i = Integer.parseInt(cmdInstructionArr[1]);
                c = new DeleteCommand(i - 1);
            } else if (command.equals("done")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                int i = Integer.parseInt(cmdInstructionArr[1]);
                c = new DoneCommand(i - 1);
            } else if (command.equals("find")) {
                Parser.checkArr(command, false, cmdInstructionArr);
                c = new FindCommand(cmdInstructionArr[1]);
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
