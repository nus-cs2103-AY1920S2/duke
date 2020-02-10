package main;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import exception.DuplicateMarkAelitaException;
import exception.EmptyInputAelitaException;
import exception.InsufficientArgumentAelitaException;
import exception.InvalidArgumentAelitaException;
import exception.InvalidCommandAelitaException;
import exception.InvalidListItemAelitaException;
import exception.IoAelitaException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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
     * Constructs a new instance of main.Duke.
     *
     * @param taskList the task list
     */
    public Duke(TaskList taskList) {

        storage = new Storage(Paths.get(System.getProperty("user.home"), "data", "duke.txt"));
        this.taskList = taskList;
        storage.loadTasks(taskList);
    }

    /**
     * Gets main.Duke's task list.
     *
     * @return main.Duke's task list.
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
                    return new ByeCommand(storage, taskList).execute();

                case "delete":
                    return new DeleteCommand(descriptionTokens, taskList).execute();

                case "done":
                    return new DoneCommand(descriptionTokens, taskList).execute();

                case "find":
                    return new FindCommand(descriptionTokens, taskList).execute();

                case "list":
                    return new ListCommand(descriptionTokens, taskList).execute();

                case "todo":
                    return new TodoCommand(descriptionTokens, taskList).execute();

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

                switch (descriptionTokens[0].toLowerCase()) {
                case "deadline":
                    return new DeadlineCommand(descriptionTokens, commandTokens[1].substring(3), taskList).execute();

                case "event":
                    return new EventCommand(descriptionTokens, commandTokens[1], taskList).execute();

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

    /**
     * Checks the array of tokens to ensure that there are at least 2 strings within it.
     *
     * @param tokens  the array to check.
     * @param context the command that called this method.
     * @throws InsufficientArgumentAelitaException if there are less than 2 strings in the token array.
     */

    private void checkSufficientTokens(String[] tokens, String context) throws InsufficientArgumentAelitaException {

        if (tokens.length < 2) {
            throw new InsufficientArgumentAelitaException(context);
        }
    }

}
