package duke.interact;

import duke.TaskList;
import duke.command.Command;
import duke.command.CommandAdd;
import duke.command.CommandBye;
import duke.command.CommandDel;
import duke.command.CommandDone;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandUpDate;
import duke.command.CommandUpDetail;
import duke.exception.DukeException;
import duke.exception.DukeExceptionCommand;
import duke.exception.DukeExceptionDate;
import duke.exception.DukeExceptionDateFormat;
import duke.exception.DukeExceptionDescription;
import duke.exception.DukeExceptionIndex;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and checks whether the user has given wrongly formatted input.
 */
public class Parser {

    /**
     * Returns a Command representing the user input.
     * @param line String input by user.
     * @param tasks Current TaskList.
     * @return Command representing user input.
     * @throws DukeException Exception thrown if user input is wrongly formatted.
     */
    public Command parse(String line, TaskList tasks) throws DukeException {
        int size = tasks.getSize();
        switch (line) {
        case "bye":
            return new CommandBye();

        case "list":
            return new CommandList();

        default:
            String[] comArs = line.split("\\s", 2);
            String inputCommand = comArs[0];

            int index;
            String details;
            String[] msgAndDate;
            String[] indexAndNew;

            switch (inputCommand) {
            case "done":
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("done");
                }
                index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("done");
                }

                assert index >= 0 : "Index should be greater than 0.";
                return new CommandDone(index);

            case "delete":
                if (comArs.length == 1) {
                    throw new DukeExceptionIndex("delete");
                }
                index = Integer.parseInt(comArs[1]) - 1;
                if (index > size - 1) {
                    throw new DukeExceptionIndex("delete");
                }

                assert index >= 0 : "Index should be greater than 0.";
                return new CommandDel(index);

            case "todo":
                details = line.substring(4);
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("todo");
                }
                return new CommandAdd(new ToDo(details));

            case "event":
                details = line.substring(5);
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("event");
                }
                msgAndDate = details.split(" /at ", 2);
                if (msgAndDate.length == 1) {
                    throw new DukeExceptionDate("event");
                }

                try {
                    LocalDate date = LocalDate.parse(msgAndDate[1]);
                    return new CommandAdd(new Event(msgAndDate[0], date));

                } catch (DateTimeParseException e) {
                    throw new DukeExceptionDateFormat();
                }

            case "deadline":
                details = line.substring(8);
                if (details.isBlank()) {
                    throw new DukeExceptionDescription("deadline");
                }
                msgAndDate = details.split(" /by ", 2);
                if (msgAndDate.length == 1) {
                    throw new DukeExceptionDate("deadline");
                }

                try {
                    LocalDate date = LocalDate.parse(msgAndDate[1]);
                    return new CommandAdd(new Deadline(msgAndDate[0], date));
                } catch (DateTimeParseException e) {
                    throw new DukeExceptionDateFormat();
                }

            case "find":
                return new CommandFind(line.substring(4));

            case "updatem":
                // implement check for presence of index & detail
                indexAndNew = comArs[1].split("\\s", 2);
                return new CommandUpDetail(Integer.parseInt(indexAndNew[0]) - 1, " " + indexAndNew[1]);

            case "updated":
                // implement check for presence of index & date
                indexAndNew = comArs[1].split("\\s", 2);
                return new CommandUpDate(Integer.parseInt(indexAndNew[0]) - 1, LocalDate.parse(indexAndNew[1]));

            default:
                throw new DukeExceptionCommand();
            }
        }
    }
}
