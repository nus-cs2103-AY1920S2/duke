package duke.command.method;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;
import duke.exception.DukeInvalidNumberOfArgumentsException;
import duke.exception.DukeNoArgumentsException;
import duke.task.DeadlineTask;

public class DeadlineCommandMethod implements CommandMethod {
    public static final String NAME = "deadline";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(DeadlineCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /by ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    DeadlineCommandMethod.NAME,2, parts.length
            );
        }
        String description = parts[0];
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDateTime by = LocalDateTime.parse(parts[1], format);
            DeadlineTask newTask = new DeadlineTask(description, by);
            return program.getTaskList().addTask(newTask);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateTimeException(parts[1]);
        }
    }
}
