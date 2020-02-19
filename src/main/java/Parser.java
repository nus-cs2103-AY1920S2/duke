import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    TaskList taskList;
    private static final String DATE_TIME_PARSE_ERROR =
            "Error parsing date and time. Please input date and time as YYYY-mm-dd hh:mm";
    private static final String EMPTY_DESCRIPTION_ERROR = "☹ OOPS!!! The description of a task cannot be empty.";
    private static final String INVALID_OP_ERROR = "Attempting to add invalid task. Operation aborted.";

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
//        this.command = new Command(taskList);
    }

    /**
     * Takes in user String input, parses, and executes command accordingly.
     * @param in User string input
     */
    /*
    public void getInput(String in) {
        if (in.equals("list")) {
            taskList.printList();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
                taskList.printDone(in);
            } else if (taskType.equals("delete")) {
                taskList.deleteTask(in);
            } else if (Task.isValidTask(taskType)) {
                taskList.addTask(in);
            } else if (in.isEmpty() || in == null) {
                System.err.println("     ☹ OOPS!!! Please type something here.");
            } else {
                System.err.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    */

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
        } else {
            try {
                String taskType = in.split(" ", 2)[0];
                String taskDesc = in.split(" ", 2)[1]; //all String after taskType
                Task newTask = null;
                if (taskType.equals("done")) {
                    Command doneCommand = new DoneCommand(taskList, taskDesc);
                    outputString = doneCommand.execute(); //TODO: outputString in this class instead
                } else if (taskType.equals("delete")) {
                    Command deleteCommand = new DeleteCommand(taskList, taskDesc);
                    outputString = deleteCommand.execute(); //TODO: outputString in this class instead
                } else if (Task.isValidTask(taskType)) {
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
                        } else { //error encountered
                            throw new DukeException(INVALID_OP_ERROR);
                        }
                    }
                    Command addCommand = new AddCommand(taskList, newTask);
                    outputString = addCommand.execute(); //TODO: outputString in this class instead
                } else if (in.isEmpty() || in == null) {
                    Command emptyCommand = new EmptyCommand();
                    outputString = emptyCommand.execute(); //TODO: outputString in this class instead
                } else {
                    Command unknownCommand = new UnknownCommand();
                    outputString = unknownCommand.execute(); //TODO: outputString in this class instead
                }
            } catch (DateTimeParseException e) {
                outputString = DATE_TIME_PARSE_ERROR;
            } catch (IndexOutOfBoundsException e) {
                outputString = EMPTY_DESCRIPTION_ERROR;
            } catch (DukeException e) {
                outputString = e.toString();
            }

        }
        assert outputString != null;
        return outputString;
    }

    /**
     * Parses taskDesc (ie. all String after task type) into 4 parts: task, preposition, date and time.
     * @param taskDesc the entire input command after task type input
     * @return string array of 4 elements in order: task, preposition, date and time
     */
    private String[] parseDetails(String taskDesc) {
        String[] details = new String[3];
        String[] taskAndDate = taskDesc.split("/",2);
        String task = taskAndDate[0].trim();
        String dateTimePrepositionString = taskAndDate[1];
        String[] dateTimePrepositionArray = dateTimePrepositionString.split(" ", 3);
        String preposition = dateTimePrepositionArray[0];
        String date = dateTimePrepositionArray[1];
        String time = dateTimePrepositionArray[2];

        details[0] = task;
        details[1] = preposition;
        details[2] = date;
        details[3] = time;

        return details;
    }

}
