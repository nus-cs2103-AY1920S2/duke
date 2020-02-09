package duke;

// packages imports
import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.FindDateCommand;
import duke.commands.ListCommand;
import duke.exceptions.DateSearchFormatException;
import duke.exceptions.EmptySearchException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.InvalidTaskNumberException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

// java imports
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Makes sense of user inputs.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Parses command line inputs into valid actions of the chat bot.
     *
     * @param taskList List to store all tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Splits the input into actions and arguments (if any).
     * Run the respective actions and outputs feedback from the action taken.
     * "List" will list all the tasks out.
     * "Done i" will mark ith task as done.
     * "Delete i" will delete the ith task.
     * "todo d" will create a Todo task with description d.
     * "event d t" will create an event with description d and start time t.
     * "deadline d dt" will create a deadline with description d and start time dt.
     * "date d" will return all the dated tasks with date d.
     *
     * @param input User inputs.
     */
    public String parseAndExecuteToString(String input) {
        String action = input.split(" ")[0];
        try {
            switch (action) {
            case "list":
                return new ListCommand(taskList).execute();
            case "done":
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new InvalidTaskNumberException(taskList.size());
                }
                assert taskNumber > 0 && taskNumber <= taskList.size(): "Task number should be between 1 and "
                            + taskList.size();

                return new DoneCommand(taskList, taskNumber - 1).execute();
            case "delete":
                taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new InvalidTaskNumberException(taskList.size());
                }
                assert taskNumber > 0 && taskNumber <= taskList.size(): "Task number should be from 1 to "
                        + taskList.size();

                return new DeleteCommand(taskList, taskNumber - 1).execute();
            case "todo":
                String[] fields = input.split("todo ");
                if (fields.length < 2) {
                    throw new EmptyDescriptionException("todo");
                }

                assert fields.length > 1: "Number of inputs should be more than 1";
                Task newTodo = new Todo(fields[1]);
                return new AddCommand(taskList, newTodo).execute();
            case "event":
            case "deadline":
                fields = input.split(action + " ");
                if (fields.length < 2) {
                    throw new EmptyDescriptionException(action);
                }

                String[] descAndTimeFields = action.equals("event")
                        ? fields[1].split(" /at ")
                        : fields[1].split(" /by ");
                assert fields.length > 1: "Number of inputs should be more than 1";

                if (descAndTimeFields.length < 2) {
                    throw new EmptyTimeException(action, descAndTimeFields);
                }
                assert descAndTimeFields.length > 1: "Number of inputs should be more than 1";

                Task newTask = action.equals("event")
                        ? new Event(descAndTimeFields[0], descAndTimeFields[1])
                        : new Deadline(descAndTimeFields[0], descAndTimeFields[1]);

                return new AddCommand(taskList, newTask).execute();
            case "date":
                fields = input.split(" ");
                if (fields.length < 2) {
                    throw new EmptySearchException();
                }

                try {
                    String date = fields[1];
                    LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    return new FindDateCommand(taskList, formattedDate).execute();
                } catch (DateTimeException ex) {
                    throw new DateSearchFormatException();
                }
            case "find":
                fields = input.split(" ");
                if (fields.length < 2) {
                    throw new EmptySearchException();
                }

                String searchWord = fields[1];
                return new FindCommand(taskList, searchWord).execute();
            default:
                throw new InvalidActionException();
            }
        } catch (InvalidActionException ex) {
            return ex.toString();
        } catch (InvalidTaskNumberException ex) {
            return ex.toString();
        }  catch (EmptySearchException ex) {
            return ex.toString();
        } catch (EmptyDescriptionException ex) {
            return ex.toString();
        } catch (EmptyTimeException ex) {
            return ex.toString();
        } catch (DateSearchFormatException ex) {
            return ex.toString();
        } catch (DateTimeException ex) {
            return "You have entered an invalid time/date format. "
                    + "Please follow the following format: 23:59 28/02/2020 (padded with zero if necessary). "
                    + "You may input '-' to omit either the time, date or both";
        }
    }
}
