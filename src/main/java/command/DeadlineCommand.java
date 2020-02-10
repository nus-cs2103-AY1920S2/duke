package command;

import main.Message;
import main.Response;
import main.TaskList;
import task.Deadline;
import task.Task;

import java.time.LocalDate;

/**
 * The Deadline command.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate date;
    private TaskList taskList;

    /**
     * Constructs a new instance of Deadline command.
     *
     * @param descriptionTokens the tokenized description.
     * @param date              the deadline.
     * @param taskList          the task list to store the new deadline task.
     */
    public DeadlineCommand(String[] descriptionTokens, String date, TaskList taskList) {

        description = reconstructDescription(descriptionTokens);
        this.taskList = taskList;
        this.date = LocalDate.parse(date);
    }

    @Override
    public Response execute() {

        Task deadLine = new Deadline(description, date);
        taskList.add(deadLine);
        return new Response(Message.ADD_TASK, deadLine);
    }

}
