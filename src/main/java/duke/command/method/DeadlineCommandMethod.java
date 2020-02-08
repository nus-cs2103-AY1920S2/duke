package duke.command.method;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;
import duke.exception.DukeInvalidNumberOfArgumentsException;
import duke.exception.DukeNoArgumentsException;
import duke.storage.Storage;
import duke.task.DeadlineTask;

public class DeadlineCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "deadline";
    }

    public String getFormat() {
        return getCommandName() + " <description> /by <datetime>";
    }

    public String getDescription() {
        return "Adds a new task with the given description and time of deadline.";
    }

    public void execute(Command command) throws DukeException {
        Duke program = Duke.getProgram();
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(getCommandName());
        }
        String[] arguments = command.getArgumentString().split(" /by ", 2);
        if (arguments.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    getCommandName(), 2, arguments.length);
        }
        String description = arguments[0];
        try {
            LocalDateTime by = LocalDateTime.parse(arguments[1],
                    DeadlineTask.DATE_TIME_INPUT_FORMAT);
            DeadlineTask newTask = new DeadlineTask(description, by);
            String message = program.getTaskList().addTask(newTask);
            program.getUi().print(message);
            Storage storage = program.getStorage();
            storage.saveTaskList(program.getTaskList());
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException(arguments[1]);
        }
    }
}
