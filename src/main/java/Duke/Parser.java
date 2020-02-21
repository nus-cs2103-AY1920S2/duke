package Duke;

import Duke.command.*;
import Duke.exception.DukeException;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    TaskList taskList;
    private static final String DATE_TIME_PARSE_ERROR =
            "Error parsing date and time. Please input date and time as YYYY-mm-dd hh:mm. (Time is optional)";
    private static final String EMPTY_DESCRIPTION_ERROR = "☹ OOPS!!! The description of a task cannot be empty.";
    private static final String INVALID_DESCRIPTION_ERROR = "☹ OOPS!!! The description of a task must be added in " +
            "the format:\nTask-type Task-description /Preposition Date Time(optional)";
    private static final String INVALID_TASK_ERROR = "Attempting to add invalid task. Operation aborted.";
    private static final String EMPTY_LIST_PROMPT = "\nList is empty! Start by creating a task first.";

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes in user String input, parses, and executes command accordingly, and returns output string by Duke.
     * @param input User string input. Typically of structure [Task type] [Task description]
     * @return Output string by Duke
     */
    String getOutputString(String input) {
        String in = input.trim();
        String commandType = in.split(" ", 2)[0].toLowerCase(); //case-insensitive to input commands
        String outputString = "";

        assert this.taskList != null;

        try {
            if (commandType.equals("bye")) {
                Command exitCommand = new ExitCommand();
                outputString = exitCommand.execute();
            } else if (commandType.equals("list")) {
                Command listCommand = new ListCommand(taskList);
                outputString = listCommand.execute();
            } else if (commandType.equals("stats")) {
                Command statsCommand = new StatsCommand(taskList);
                outputString = statsCommand.execute();
            } else if (commandType.equals("help")) {
                Command helpCommand = new HelpCommand();
                outputString = ((HelpCommand) helpCommand).executeSimple();
            } else if (commandType.equals("help-detailed")) {
                Command helpCommand = new HelpCommand();
                outputString = helpCommand.execute();
            } else {
                if (Task.isValidTask(commandType)) {
                    String taskDesc = in.split(" ", 2)[1]; //all Strings after commandType
                    Task newTask = createTask(commandType, taskDesc);
                    Command addCommand = new AddCommand(taskList, newTask);
                    outputString = addCommand.execute();
                } else if (isValidCommand(commandType)) {
                    if (commandType.equals("done")) {
                        Command doneCommand = new DoneCommand(taskList, in);
                        outputString = doneCommand.execute();
                    } else if (commandType.equals("delete")) {
                        Command deleteCommand = new DeleteCommand(taskList, in);
                        outputString = deleteCommand.execute();
                    } else if (commandType.equals("find")) {
                        Command findCommand = new FindCommand(taskList, in);
                        outputString = findCommand.execute();
                    } else {

                    }
                } else if (in.isEmpty() || in == null) {
                    Command emptyCommand = new EmptyCommand();
                    outputString = emptyCommand.execute();
                } else {
                    Command unknownCommand = new UnknownCommand();
                    outputString = unknownCommand.execute();
                }
            }
        } catch (DateTimeParseException e) {
            outputString = DATE_TIME_PARSE_ERROR;
        } catch (IndexOutOfBoundsException e) { //from null taskDesc
            outputString = EMPTY_DESCRIPTION_ERROR;
        } catch (DukeException e) {
            outputString = e.getMessage();
        }
        finally {
            if (taskList.getTaskList().isEmpty()) { //Prompt user to add task if list is empty
                outputString += EMPTY_LIST_PROMPT;
            }
            assert outputString != null;
            return outputString;
        }
    }

    private static Task createTask(String taskType, String taskDesc) throws DukeException {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(taskDesc);
        } else {
            String[] taskDetails = parseDetails(taskDesc);
            String task = taskDetails[0];
            String preposition = taskDetails[1];
            String date = taskDetails[2];
            String time = taskDetails[3];

            LocalDate formattedDate = LocalDate.parse(date);

            if (taskType.equals("deadline")) {
                newTask = new Deadline(task, preposition, formattedDate, time);
            } else if (taskType.equals("event")) {
                newTask = new Event(task, preposition, formattedDate, time);
            } else { //error encountered creating task
                throw new DukeException(INVALID_TASK_ERROR);
            }
        }
        return newTask;
    }

    /**
     * Parses taskDesc (ie. all String after task type) into 4 parts: task, preposition, date and time.
     * @param taskDesc the entire input command after task type input
     * @return string array of 4 elements in order: task, preposition, date and time
     */
    private static String[] parseDetails(String taskDesc) throws DukeException {
        try {
            String[] details = new String[4];
            String[] taskAndDate = taskDesc.split("/", 2);
            String task = taskAndDate[0].trim();
            String dateTimePrepositionString = taskAndDate[1];
            String[] dateTimePrepositionArray = dateTimePrepositionString.split(" ", 3);
            String preposition = dateTimePrepositionArray[0];
            String date = dateTimePrepositionArray[1];

            details[0] = task;
            details[1] = preposition;
            details[2] = date;

            if (dateTimePrepositionArray.length == 3) { //case when time is provided
                String time = dateTimePrepositionArray[2];
                details[3] = time;
            }

            return details;

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_DESCRIPTION_ERROR);
        }
    }

    private static boolean isValidCommand(String taskType) {
        return taskType.equals("done") || taskType.equals("delete") || taskType.equals("find");
    }
}
