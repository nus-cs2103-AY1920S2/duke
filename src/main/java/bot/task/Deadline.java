package bot.task;

import bot.util.PrettyTime;

/**
 * Class that represents
 * "Deadline" type Tasks
 */
public class Deadline extends Task {
    public static final String BY = "/by ";
    public static final String TYPE = "D";

    /**
     * Constructor for a Deadline Task
     *
     * @param td String representing the Deadline description
     * @param tt String representing the Deadline time
     */
    public Deadline(String td, String tt) {
        this(td, new PrettyTime(tt));
    }

    /**
     * Constructor for a Deadline Task
     *
     * @param taskDesc Description of the Deadline
     * @param taskTime Time of the actual Deadline
     */
    public Deadline(String taskDesc, PrettyTime taskTime) {
        super(taskDesc, taskTime);
    }

    @Override
    public String getType() {
        return Deadline.TYPE;
    }

    @Override
    public String getTimeVerb(String rawTime) {
        return "(by: " + rawTime + ")";
    }
}
