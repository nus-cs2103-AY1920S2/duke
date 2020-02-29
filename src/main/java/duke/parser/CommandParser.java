package duke.parser;

import duke.entity.command.*;
import duke.entity.task.Deadline;
import duke.entity.task.Event;
import duke.entity.task.Todo;
import duke.handler.Ui;

import java.text.ParseException;
import java.util.Date;

public class CommandParser {

    private DateTimeParser dateTimeParser = new DateTimeParser();
    private Ui ui = new Ui();

    public CommandParser() {
    }

    /**
     * Parses the user input to determine which command to be created, together with required information to carry out task
     *
     * @param command next line of user input
     * @return the Command object created based on user input
     */
    public Command parse(String command) {
        int breakPoint = command.indexOf('/');
        String[] nextLine = command.split("\\s+");
        if (nextLine[0].equals("list")) {
            return new ListCommand();
        } else if (nextLine[0].equals("done")) {
            int index = Integer.parseInt(nextLine[1]) - 1;
            return new DoneCommand(index);
        } else if (nextLine[0].equals("delete")) {
            int index = Integer.parseInt(nextLine[1]) - 1;
            return new DeleteCommand(index);

        } else if (nextLine[0].equals("check")) {
            //check all events and deadlines before/on date
            try {
                Date date = dateTimeParser.parseDate(nextLine[1]);
                return new CheckCommand(date);
            } catch (ParseException e) {
                ui.invalidFormatError();
            } catch (IndexOutOfBoundsException e) {
                ui.invalidFormatError();
            }
        } else if (nextLine[0].equals("bye")) {
            return new ExitCommand();
        } else if (nextLine[0].equals("find")) {
            return new FindCommand(nextLine[1]);
        } else {
            if (nextLine[0].equals("todo") || nextLine[0].equals("t")) {
                return new AddCommand(new Todo(command.substring(5)));
            } else if (nextLine[0].equals("event") || nextLine[0].equals("e")) {
                if (breakPoint != -1) {
                    return new AddCommand(new Event(command.substring(6, breakPoint - 1), command.substring(breakPoint + 4)));
                } else {
                    ui.missingDetails();
                }
            } else if (nextLine[0].equals("deadline") || nextLine[0].equals("d")) {
                if (breakPoint != -1) {
                    return new AddCommand(new Deadline(command.substring(9, breakPoint - 1), command.substring(breakPoint + 4)));
                } else {
                    ui.missingDetails();
                }
            }
        }
        return null;
    }
}
