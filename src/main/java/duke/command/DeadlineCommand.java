package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingByDeadlineException;
import duke.exception.MissingDetailsException;
import duke.exception.UnknownDateTimeException;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    /**
     * deadlineCommand Method creates Deadline Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws DukeException when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public static String run(TaskList taskList, String commandSuffix) throws DukeException {
        try {
            String[] byDeadline = commandSuffix.split(" /by ");

            try {
                //Initialising proTip; also used as a silent check for MissingByDeadlineException
                String proTip = byDeadline[1];
                boolean unknownDate = false;

                try {
                    try {
                        DateTimeFormatter inputdTFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime outputDT = LocalDateTime.parse(byDeadline[1], inputdTFormat);
                        DateTimeFormatter outputdTFormat = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma");
                        byDeadline[1] = outputDT.format(outputdTFormat);
                    } catch (DateTimeParseException e) {
                        throw new UnknownDateTimeException();
                    }
                } catch (DukeException e) {
                    unknownDate = true;
                    proTip = e.toString();
                }

                taskList.add(new Deadline(false, taskList.size(), byDeadline[0], byDeadline[1]));

                String deadlineOutput = ("Got it. I've added this task:\n\t[D][✗] "
                    + byDeadline[0] + " (by: " + byDeadline[1] + ")"
                    + "\nNow you have " + taskList.size() + " task(s) in the list.");

                if (unknownDate) {
                    deadlineOutput = deadlineOutput + "\nPS: " + proTip;
                }

                return deadlineOutput;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingByDeadlineException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }
}
