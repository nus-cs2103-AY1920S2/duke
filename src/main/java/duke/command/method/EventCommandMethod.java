package duke.command.method;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;
import duke.exception.DukeInvalidNumberOfArgumentsException;
import duke.exception.DukeNoArgumentsException;
import duke.task.EventTask;

public class EventCommandMethod implements CommandMethod {
    public static final String NAME = "event";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(EventCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /at ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    EventCommandMethod.NAME, 2, parts.length
            );
        }
        String description = parts[0];
        try {
            LocalDateTime at = LocalDateTime.parse(parts[1],
                    EventTask.DATE_TIME_INPUT_FORMAT);
            EventTask newTask = new EventTask(description, at);
            return program.getTaskList().addTask(newTask);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException(parts[1]);
        }
    }
}
