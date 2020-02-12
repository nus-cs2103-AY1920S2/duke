package bot.task;

import bot.util.PrettyTime;

/**
 * Class that represents
 * "To-do" type Tasks
 */
public class Todo extends Task {
    public static String TYPE = "T";

    /**
     * Constructor for a To-do Task
     *
     * @param td String representing description
     *           of this To-do Task
     */
    public Todo(String td) {
        super(td, new PrettyTime(""));
    }

    @Override
    public String getType() {
        return Todo.TYPE;
    }

    @Override
    public String getTimeVerb(String rawTime) {
        // no timeVerb as To-do Tasks are
        // not associated with a time
        return "";
    }
}
