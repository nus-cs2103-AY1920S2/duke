import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The main class of the project.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new instance of Duke.
     *
     * @param taskList the task list
     */
    public Duke(TaskList taskList) {

        storage = new Storage(Paths.get(System.getProperty("user.home"), "data", "duke.txt"));
        this.taskList = taskList;
        storage.loadTasks(taskList);
    }

    /**
     * Gets Duke's task list.
     *
     * @return Duke's task list.
     */
    public TaskList getTaskList() {

        return taskList;
    }

    /**
     * Executes the command.
     *
     * @param command the string command supplied by the user.
     * @return the relevant response after processing the command.
     */
    public Response executeCommand(String command) {

        try {
            //Split the command tokens into description and time portions
            String[] commandTokens = Parser.parse(command, "/");

            //Further split description into words for command checking
            String[] descriptionTokens = Parser.parse(commandTokens[0], " ");

            if (commandTokens.length < 2) { //Regular commands and todo

                switch (descriptionTokens[0].toLowerCase()) {
                case "bye":
                    storage.saveTasks(taskList);
                    return new Response(Message.GOODBYE, null);

                case "delete":
                    //Check if there are sufficient tokens
                    checkSufficientTokens(descriptionTokens, "delete");

                    int index = Integer.parseInt(descriptionTokens[1]) - 1;
                    return new Response(Message.DELETE, taskList.remove(index));

                case "done":
                    //Check if there are sufficient tokens
                    checkSufficientTokens(descriptionTokens, "done");

                    int indx = Integer.parseInt(descriptionTokens[1]) - 1;
                    taskList.complete(indx);
                    return new Response(Message.DONE, indx);

                case "find":
                    //Check if there are sufficient tokens
                    checkSufficientTokens(descriptionTokens, "find");

                    TaskList results = taskList.list(descriptionTokens[1].toLowerCase());
                    if (results.getSize() > 0) {
                        return new Response(Message.TASK_FOUND, results);
                    } else {
                        return new Response(Message.TASK_NOT_FOUND, null);
                    }

                case "list":
                    if (taskList.getSize() > 0) {
                        if (descriptionTokens.length == 2) {
                            //Date is provided
                            LocalDate date = LocalDate.parse(descriptionTokens[1]);
                            TaskList tmp = taskList.list(date);
                            if (tmp.getSize() > 0) {
                                return new Response(Message.TASK_FOUND, tmp);
                            } else {
                                return new Response(Message.NO_TASK_ON_DATE, null);
                            }
                        } else {
                            return new Response(Message.LIST, taskList);
                        }
                    } else {
                        return new Response(Message.NO_TASK, null);
                    }

                case "todo":
                    checkSufficientTokens(descriptionTokens, "todo");

                    Task task = new Todo(reconstructDescription(descriptionTokens));
                    taskList.add(task);
                    return new Response(Message.ADD_TASK, task);

                case "deadline":
                    //Fallthrough
                case "event":
                    //Check if description is present. Throws exception if description missing.
                    checkSufficientTokens(descriptionTokens, "deadline");
                    //Date portion is missing as size commandToken array is 1.
                    throw new InsufficientArgumentAelitaException("date");

                default:
                    //No command matches input.
                    throw new InvalidCommandAelitaException();
                }

            } else {
                //Check if there are descriptions. Throws exception if there isn't.
                checkSufficientTokens(descriptionTokens, "deadline");

                //Concat the description tokens back to one string
                String description = reconstructDescription(descriptionTokens);

                switch (descriptionTokens[0].toLowerCase()) {
                case "deadline":
                    LocalDate deadlineDate = LocalDate.parse(commandTokens[1].substring(3));
                    Task deadline = new Deadline(description, deadlineDate);
                    taskList.add(deadline);
                    return new Response(Message.ADD_TASK, deadline);

                case "event":
                    String[] dateTime = extractDateTime(commandTokens[1]);
                    LocalDate eventDate = LocalDate.parse(dateTime[0]);

                    Task event = new Event(description, eventDate, dateTime[1], dateTime[2]);
                    taskList.add(event);
                    return new Response(Message.ADD_TASK, event);

                default:
                    //No command matches input.
                    throw new InvalidCommandAelitaException();
                }

            }
        } catch (EmptyInputAelitaException e) {
            return new Response(Message.EMPTY_COMMAND, null);

        } catch (IoAelitaException e) {
            return new Response(Message.IO_ERROR, null);

        } catch (InsufficientArgumentAelitaException e) {
            switch (e.getMessage()) {
            case "date":
                return new Response(Message.MISSING_DATE, null);

            case "date-time":
                return new Response(Message.MISSING_DATE_TIME, null);

            case "deadline":
                //Fallthrough
            case "todo":
                return new Response(Message.MISSING_DESCRIPTION, null);

            case "delete":
                return new Response(Message.MISSING_DELETE_INDEX, null);

            case "done":
                return new Response(Message.MISSING_DONE_INDEX, null);

            case "end time":
                return new Response(Message.MISSING_END_TIME, null);

            case "find":
                return new Response(Message.MISSING_FIND_INDEX, null);

            default:
                //Will not reach here
                return null;
            }

        } catch (InvalidListItemAelitaException e) {
            return new Response(Message.ITEM_NOT_FOUND, null);

        } catch (DuplicateMarkAelitaException e) {
            return new Response(Message.TASK_COMPLETED, null);

        } catch (InvalidCommandAelitaException e) {
            return new Response(Message.COMMAND_NOT_RECOGNIZED, null);

        } catch (InvalidArgumentAelitaException e) {
            return new Response(Message.INVALID_ARGUMENT, null);

        } catch (DateTimeParseException e) {
            return new Response(Message.DATE_NOT_RECOGNIZED, null);

        } catch (NumberFormatException e) {
            return new Response(Message.INDEX_NAN, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkSufficientTokens(String[] tokens, String context) throws InsufficientArgumentAelitaException {

        if (tokens.length < 2) {
            throw new InsufficientArgumentAelitaException(context);
        }
    }

    /**
     * Extract the date and time from a string comprising of the date and time.
     *
     * @param dateTime The string containing the date and time.
     * @return An array of size 3 containing the date, start time and end time respectively.
     * @throws InsufficientArgumentAelitaException if the date or time is missing or incomplete.
     */
    private String[] extractDateTime(String dateTime) throws InsufficientArgumentAelitaException {

        //A local placeholder for date and time of Event {date, start time, end time}
        String[] tmp = new String[3];

        //Get the date
        String[] dateTimeTokens = dateTime.split(" ");
        tmp[0] = dateTimeTokens[1]; //Date

        if (dateTimeTokens.length < 3) {
            //One of the date-time components is missing
            throw new InsufficientArgumentAelitaException("date-time");
        }

        //Separate start time and end time
        String[] timeTokens = dateTimeTokens[2].split("-");
        if (timeTokens.length < 2) {
            throw new InsufficientArgumentAelitaException("end time");
        }

        tmp[1] = timeTokens[0]; //Start time
        tmp[2] = timeTokens[1]; //End time
        return tmp;
    }

    /**
     * Reconstructs the description into a string from an array of words.
     *
     * @param descriptionTokens An array of words of the description.
     * @return The string form of the description.
     */
    private String reconstructDescription(String[] descriptionTokens) {

        StringBuilder builder = new StringBuilder(descriptionTokens[1]);

        for (int i = 2; i < descriptionTokens.length; i++) {
            builder.append(" ");
            builder.append(descriptionTokens[i]);
        }
        return builder.toString();
    }

}
