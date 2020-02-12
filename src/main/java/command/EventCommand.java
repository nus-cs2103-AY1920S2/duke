package command;

import exception.InsufficientArgumentAelitaException;
import main.Message;
import main.Response;
import main.TaskList;
import task.Event;
import task.Task;

import java.time.LocalDate;

/**
 * The Event command.
 */
public class EventCommand extends Command {

    private String description;
    private String at;
    private TaskList taskList;

    /**
     * Constructs a new instance of Event command.
     *
     * @param descriptionTokens the tokenized description.
     * @param at                the dead and time of the event.
     * @param taskList          the task list to store the new Event task.
     */
    public EventCommand(String[] descriptionTokens, String at, TaskList taskList) {

        this.description = reconstructDescription(descriptionTokens);
        this.at = at;
        this.taskList = taskList;
    }

    @Override
    public Response execute() throws InsufficientArgumentAelitaException {

        String[] dateTime = extractDateTime(at);
        LocalDate eventDate = LocalDate.parse(dateTime[0]);

        assert dateTime.length == 3 : "There should be a date, start time and end time";

        Task event = new Event(description, eventDate, dateTime[1], dateTime[2]);
        taskList.add(event);
        return new Response(Message.ADD_TASK, "event");
    }

    /**
     * Extracts the date and time from a string comprising of the date and time.
     *
     * @param dateTime The string containing the date and time.
     * @return An array of size 3 containing the date, start time and end time respectively.
     * @throws InsufficientArgumentAelitaException if the date or time is missing or incomplete.
     */
    private String[] extractDateTime(String dateTime) throws InsufficientArgumentAelitaException {

        //A local placeholder for date and time of task.Event {date, start time, end time}
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

}
