package parser;

import entity.command.*;
import entity.task.Deadline;
import entity.task.Event;
import entity.task.Todo;
import handler.Ui;

import java.text.ParseException;
import java.util.Date;

public class CommandParser {

    private DateTimeParser dateTimeParser;
    private Ui ui;

    public CommandParser(Ui ui) {
        this.dateTimeParser = new DateTimeParser();
        this.ui = ui;
    }

    public boolean terminateUI(String command) {
        return !command.equals("bye");
    }

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
        } else {
            if (nextLine[0].equals("todo")) {
                return new AddCommand(new Todo(command.substring(5)));
            } else if (nextLine[0].equals("event")) {
                if (breakPoint != -1) {
                    return new AddCommand(new Event(command.substring(6, breakPoint - 1), command.substring(breakPoint + 4)));
                } else {
                    ui.missingDetails();
                }
            } else if (nextLine[0].equals("deadline")) {
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
