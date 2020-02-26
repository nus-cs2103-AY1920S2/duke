package bot.task;

import bot.util.PrettyTime;

/**
 * Class that represents
 * "Event" type Tasks
 */
public class Event extends Task {
    public static final String AT = "/at ";
    public static final String TYPE = "E";

    /**
     * Constructor for a Deadline Task
     *
     * @param td String representing the Event description
     * @param tt String representing the Event time
     */
    public Event(String td, String tt) {
        this(td, new PrettyTime(tt));
    }

    /**
     * Constructor for a Event Task
     *
     * @param taskDesc Description of the Event
     * @param taskTime Time of the Event
     */
    public Event(String taskDesc, PrettyTime taskTime) {
        super(taskDesc, taskTime);
    }

    @Override
    public String getType() {
        return Event.TYPE;
    }

    @Override
    public String getTimeVerb(String rawTime) {
        return "(at: " + rawTime + ")";
    }
}
