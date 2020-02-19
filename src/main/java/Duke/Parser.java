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

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes in user String input, parses, and executes command accordingly, and returns output string by Duke.
     * @param input User string input. Typically of structure [Task type] [Task description]
     * @return Output string by Duke
     */
    String getOutputString(String input) {
        String in = input.trim().toLowerCase();
        String outputString;
      
        assert this.taskList != null;
        if (in.equals("bye")) {
            Command exitCommand = new ExitCommand();
            outputString = exitCommand.execute(); //TODO: outputString in this class instead
        } else if (in.equals("list")) {
            Command listCommand = new ListCommand(taskList);
            outputString = listCommand.execute(); //TODO: outputString in this class instead
        } else if (in.equals("statistics") || in.equals("stats")) {
            Command statsCommand = new StatsCommand(taskList);
            outputString = statsCommand.execute(); //TODO: outputString in this class instead
        } else if (in.equals("help")) {
            Command helpCommand = new HelpCommand();
            outputString = helpCommand.execute();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (isValidTask(taskType)) {
                try {
                    String taskDesc = in.split(" ", 2)[1]; //all String after taskType
                    if (taskType.equals("done")) {
                        Command doneCommand = new DoneCommand(taskList, taskDesc);
                        outputString = doneCommand.execute(); //TODO: outputString in this class instead
                    } else if (taskType.equals("delete")) {
                        Command deleteCommand = new DeleteCommand(taskList, taskDesc);
                        outputString = deleteCommand.execute(); //TODO: outputString in this class instead
                    } else {
                        Task newTask = createTask(taskType, taskDesc);
                        Command addCommand = new AddCommand(taskList, newTask);
                        outputString = addCommand.execute(); //TODO: outputString in this class instead
                    }
                } catch (DateTimeParseException e) {
                    outputString = DATE_TIME_PARSE_ERROR;
                } catch (IndexOutOfBoundsException e) {
                    outputString = EMPTY_DESCRIPTION_ERROR;
                } catch (DukeException e) {
                    outputString = e.toString();
                }
            } else if (in.isEmpty() || in == null) {
                Command emptyCommand = new EmptyCommand();
                outputString = emptyCommand.execute(); //TODO: outputString in this class instead
            } else {
                Command unknownCommand = new UnknownCommand();
                outputString = unknownCommand.execute(); //TODO: outputString in this class instead
            }
        }
        assert outputString != null;
        return outputString;
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

    private static boolean isValidTask(String taskType) {
        return taskType.equals("done") || taskType.equals("delete") || Task.isValidTask(taskType);
    }
}
