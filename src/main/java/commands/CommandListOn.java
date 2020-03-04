package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Command that prints the list of tasks that occur on the selected date.
 */
public class CommandListOn implements Command {
    /**
     * Date entered is parsed first, and then the TaskList is searched to check if any deadlines or events occur on
     * that date.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException Throws an exception if the date entered is in the incorrect format.
     */
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        try {
            LocalDate searchDate = LocalDate.parse(argsArray[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            List<Task> filteredTaskList = processor.getTaskList().getTasksOn(searchDate);

            if (filteredTaskList.size() == 0) {
                return "Looks like you don't have any tasks entered on this date! Try entering one with "
                        + "the commands 'todo', 'deadline' or 'event'!";
            }

            String filteredTaskListString = "";
            for (int i = 0; i < filteredTaskList.size(); i++) {
                filteredTaskListString += String.format("%d. %s", i + 1, filteredTaskList.get(i)) + "\n";
            }

            String output = String.format("%s\n%s\n", String.format("Here are the %d tasks I've noted down for "
                            + "you on %s:", filteredTaskList.size(), searchDate.format(
                    DateTimeFormatter.ofPattern("MMM d yyyy"))),
                    filteredTaskListString);

            return output;
        } catch (Exception e) {
            throw new DukeException("You've entered an incorrect date! Please follow this format: liston dd/MM/yyyy");
        }


    }
}
