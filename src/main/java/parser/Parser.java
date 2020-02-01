package parser;

import commands.*;
import dukeexception.DukeException;
import dukeexception.InvalidDateTimeException;
import dukeexception.WrongInputException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() { }

    public static Command parse(String fullCommand) throws DukeException {
        Command com = null;
        String[] temp = fullCommand.split(" ");
        String command = temp[0];

        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter inputTimeFormat = DateTimeFormatter.ofPattern("HHmm");
        String[] arr;
        LocalDate ld;
        LocalTime lt;

        try {
            if (command.equals("delete")) {
                Parser.checkInput(fullCommand, command, temp.length);
                com = new DeleteCommand(Integer.parseInt(temp[1]));
            } else if (command.equals("done")) {
                checkInput(fullCommand, command, temp.length);
                com = new DoneCommand(Integer.parseInt(temp[1]));
            } else if (command.equals(("todo"))) {
                checkInput(fullCommand, command, temp.length);
                Task t = new Todo(fullCommand.substring(5));
                com = new AddCommand(t);
            } else if (command.equals("deadline")) {
                checkInput(fullCommand, command, temp.length);
                arr = fullCommand.split(" /by ");
                String[] dt = arr[1].split(" ");
                if (dt.length < 2) {
                    throw new WrongInputException(fullCommand);
                }
                ld = LocalDate.parse(dt[0], inputDateFormat);
                lt = LocalTime.parse(dt[1], inputTimeFormat);
                Task d = new Deadline(arr[0].substring(9), ld, lt);
                com = new AddCommand(d);
            } else if (command.equals("event")) {
                checkInput(fullCommand, command, temp.length);
                arr = fullCommand.split(" /at ");
                String[] dt = arr[1].split(" ");
                if (dt.length < 2) {
                    throw new WrongInputException(fullCommand);
                }
                ld = LocalDate.parse(dt[0], inputDateFormat);
                lt = LocalTime.parse(dt[1], inputTimeFormat);
                Task e = new Event(arr[0].substring(6), ld, lt);
                com = new AddCommand(e);
            } else if (command.equals("search")) {
                checkInput(fullCommand, command, temp.length);
                String description = fullCommand.substring(command.length() + 1);
                com = new SearchCommand(LocalDate.parse(description, inputDateFormat));
            } else if (command.equals("list")) {
                com = new ListCommand();
            } else if (command.equals("bye")) {
                com = new ExitCommand();
            } else {
                com = new UnknownCommand();
            }

        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        return com;
    }

    private static void checkInput(String fullCommand, String command, int noOfArg) throws DukeException {
        if (command.equals("done") || command.equals("delete") ||
                command.equals("todo") || command.equals("search")) {
            if (noOfArg <= 1) {
                throw new WrongInputException(fullCommand);
            }
        } else if (command.equals("deadline")) {
            if (!fullCommand.contains("/by")) {
                throw new WrongInputException(fullCommand);
            }

        } else if (command.equals("event")) {
            if (!fullCommand.contains("/at")) {
                throw new WrongInputException(fullCommand);
            }
        }
    }


}
