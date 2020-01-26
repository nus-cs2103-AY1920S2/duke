package commands;

import exceptions.DukeException;
import processor.DukeProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Command that prints the list of tasks that occur on the selected date.
 */
public class CommandListOn implements Command {
    /**
     * Date entered is parsed first, and then the TaskList is searched to check if any deadlines or events occur on
     * that date.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException
     */
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        try {
            LocalDate searchDate = LocalDate.parse(argsArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            processor.getTaskList().printTasksOn(searchDate);
        } catch(Exception e) {
            e.printStackTrace();
            throw new DukeException("You've entered an incorrect date! Please follow this format: liston dd/MM/yyyy");
        }


    }
}
