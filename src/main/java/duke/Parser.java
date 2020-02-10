package duke;

// packages imports
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExceptionCommand;
import duke.commands.FindCommand;
import duke.commands.FindDateCommand;
import duke.commands.ListCommand;
import duke.commands.Command;
import duke.exceptions.DateSearchFormatException;
import duke.exceptions.EmptySearchException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.InvalidTaskNumberException;
import duke.tasks.TaskList;

// java imports
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
    public Command parse(String input) {
        List<String> validatedInput = validateInput(input);
        String action =  validatedInput.remove(0);
        switch (action) {
        case "list":
            return new ListCommand(taskList);
        case "done":
            return new DoneCommand(taskList, validatedInput);
        case "delete":
            return new DeleteCommand(taskList, validatedInput);
        case "todo":
            return new AddTodoCommand(taskList, validatedInput);
        case "event":
            return new AddEventCommand(taskList, validatedInput);
        case "deadline":
            return new AddDeadlineCommand(taskList, validatedInput);
        case "find":
            return new FindCommand(taskList, validatedInput);
        case "date":
            return new FindDateCommand(taskList, validatedInput);
        case "bye":
            return new ByeCommand();
        default:
            return new ExceptionCommand(validatedInput);
        }
    }

    /**
     * Validates the input of the user before parsing it as a command.
     *
     * @param input User input.
     * @return Necessary details of the user command.
     */
    public List<String> validateInput(String input) {
        String action = input.split(" ")[0];
        List<String> data = new ArrayList<>();
        try {
            switch (action) {
            case "list":
            case "bye":
                break;
            case "done":
            case "delete":
                String[] fields = input.split(" ");
                if (fields.length < 2) {
                    throw new InvalidTaskNumberException(taskList.size());
                }

                int taskNumber = Integer.parseInt(fields[1]);
                if (taskNumber < 1 || taskNumber > taskList.size()) {
                    throw new InvalidTaskNumberException(taskList.size());
                }
                assert taskNumber > 0 && taskNumber <= taskList.size() : "Task number should be from 1 to "
                        + taskList.size();

                data.add(String.valueOf(taskNumber));
                break;
            case "todo":
                fields = input.split("todo ");
                if (fields.length < 2) {
                    throw new EmptyDescriptionException("todo");
                }
                assert fields.length > 1 : "Number of inputs should be more than 1";

                String description = fields[1];
                data.add(description);
                break;
            case "event":
            case "deadline":
                // Validate description
                fields = input.split(action + " ");
                if (fields.length < 2) {
                    throw new EmptyDescriptionException(action);
                }
                assert fields.length > 1 : "Number of inputs should be more than 1";

                // Validate existence of time
                String[] descAndTimeFields = action.equals("event")
                        ? fields[1].split(" /at ")
                        : fields[1].split(" /by ");

                if (descAndTimeFields.length < 2) {
                    throw new EmptyTimeException(action);
                }
                assert descAndTimeFields.length > 1 : "Number of inputs should be more than 1";

                // Validate existence of time and date
                String[] timeAndDateFields = descAndTimeFields[1].split(" ");
                if (timeAndDateFields.length < 2) {
                    throw new EmptyTimeException(action);
                }
                assert timeAndDateFields.length > 1 : "Number of inputs should be more than 1";

                // Validate format of time and date; throws DateTimeException if invalid
                try {
                    // Test for validity by catching DateTimeParseException if any
                    LocalTime.parse(timeAndDateFields[0]);
                    formatDate(timeAndDateFields[1]);

                    description = descAndTimeFields[0];
                    data.add(description);
                    data.add(timeAndDateFields[0]);
                    data.add(timeAndDateFields[1]);
                } catch (DateTimeException ex) {
                    throw new DateSearchFormatException();
                }
                break;
            case "date":
                fields = input.split(" ");
                if (fields.length < 2) {
                    throw new EmptySearchException();
                }

                String date = fields[1];
                try {
                    LocalDate formattedDate = formatDate(date);
                    data.add(formattedDate.toString());
                } catch (DateTimeException ex) {
                    throw new DateSearchFormatException();
                }
                break;
            case "find":
                fields = input.split(" ");
                if (fields.length < 2) {
                    throw new EmptySearchException();
                }

                String searchWord = fields[1];
                data.add(searchWord);
                break;
            default:
                throw new InvalidActionException();
            }
        } catch (NumberFormatException ex) {
            data.add("exception");
            data.add(new InvalidTaskNumberException(taskList.size()).toString());
            return data;
        } catch (DateTimeException ex) {
            data.add("exception");
            data.add(new DateSearchFormatException().toString());
        } catch (Exception ex) {
            data.add("exception");
            data.add(ex.toString());
            return data;
        }

        data.add(0, action);
        return data;
    }

    private LocalDate formatDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
