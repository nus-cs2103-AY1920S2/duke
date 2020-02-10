package command;

import main.Message;
import main.Response;
import main.TaskList;

import java.time.LocalDate;

/**
 * The List command.
 */
public class ListCommand extends Command {

    private String[] commandTokens;
    private TaskList taskList;

    /**
     * Constructs a new instance of List command.
     *
     * @param commandTokens the tokenized command.
     * @param taskList      the task list to list from.
     */
    public ListCommand(String[] commandTokens, TaskList taskList) {

        this.commandTokens = commandTokens;
        this.taskList = taskList;
    }

    @Override
    public Response execute() {

        if (taskList.getSize() > 0) {
            //There are tasks in the list
            if (commandTokens.length == 2) {
                //The date is provided in the command
                return listWithDate(taskList, LocalDate.parse(commandTokens[1]));

            } else {
                return listAll(taskList);

            }
        } else {
            return new Response(Message.NO_TASK, null);
        }
    }

    private Response listAll(TaskList taskList) {

        return new Response(Message.LIST, taskList);
    }

    /**
     * List out the tasks that are related to the given date.
     *
     * @param taskList the task list to search in.
     * @param date     the date of interest
     * @return task found if there are tasks related to the date and task not found if otherwise.
     */

    private Response listWithDate(TaskList taskList, LocalDate date) {

        TaskList result = taskList.list(date);
        if (result.getSize() > 0) {
            return new Response(Message.TASK_FOUND, result);
        } else {
            return new Response(Message.NO_TASK_ON_DATE, null);
        }
    }

}
