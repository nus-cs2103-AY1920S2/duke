import java.util.Arrays;
import java.util.regex.Pattern;

public class Parser {
    public static String parseTask(Task task) { // parses task into string to load into storage
        String parsed;
        TaskType taskType = task.getTaskType();
        switch (taskType) {
            case TODO:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription();
                break;
            case EVENT:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Event) task).getTaskTime();
                break;
            case DEADLINE:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Deadline) task).getTaskTime();
                break;
            default:
                parsed = ""; // TODO add error message, change DukeException to such task exists
        }
        return parsed;
    }

    public static Task parseFile(String line) { // parses line from tasks.txt into a task
        String[] split = line.split(Pattern.quote("|"));
        switch (split[0]) {
            case "T":
                return new ToDo(split[1], split[2]);
            case "E":
                return new Event(split[1], split[2], split[3]);
            case "D":
                return new Deadline(split[1], split[2], split[3]);
            default:
                System.out.println("Error in parser parse method"); // TODO add error message and DukeException
                return null;
        }
    }

    public static Command parseInput(String input) throws DukeException {
        try {
            input = input.trim(); // trim any opening and trailing whitespace
            if (input.equals("bye")) { // parse exit command
                return new ExitCommand();
            }
            else if (input.equals("list")) { // parse list command
                return new ListCommand();
            }
            else if (input.startsWith("done")) { // parse done command
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                return new DoneCommand(taskNumber);
            }
            else if (input.startsWith("delete")) { // parse delete command
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                return new DeleteCommand(taskNumber);
            }
            else { // parse task command
                    String[] split = input.split(" ", 2);
                    TaskType taskType = TaskType.valueOf(split[0].toUpperCase());
                    String taskDetails = split[1];
                    Task task = new Task();
                    switch (taskType) {
                        case TODO:
                            task = new ToDo(taskDetails);
                            break;
                        case EVENT:
                            String[] eventDetails = taskDetails.split("/at");
                            String eventDescription = eventDetails[0].trim();
                            String eventTime = eventDetails[1].trim();
                            task = new Event(eventDescription, eventTime);
                            break;
                        case DEADLINE:
                            String[] deadlineDetails = taskDetails.split("/by");
                            String deadlineDescription = deadlineDetails[0].trim();
                            String deadline = deadlineDetails[1].trim();
                            task = new Deadline(deadlineDescription, deadline);
                            break;
                    }

                    return new AddCommand(task);

            }
        }
        catch(IndexOutOfBoundsException e) { // catch insufficient commands given
            throw new DukeException(DukeError.INSUFFICIENT);
        }
        catch(IllegalArgumentException e) { // catch wrong command given
            throw new DukeException(DukeError.COMMAND);
        }
    }

}