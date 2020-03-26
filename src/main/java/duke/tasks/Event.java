package duke.tasks;

import duke.utils.StringParser;

/**
 * The <code>Event</code> class extends from Task.
 *
 * <p>An <code>Event</code> object has a <code>name</code>, a <code>isCompleted</code> property
 * and an <code>at</code> property which represents the time of the event.
 *
 * @author Zhu Yijie
 */
public class Event extends Task {
    private static final String IDENTIFIER = "E";
    public static final String TIME_DEMARCATOR = "/at";
    
    private String at;
    
    /**
     * Creates a new Event object.
     *
     * @param name The name of the event.
     * @param at Time of event.
     */
    public Event(String name, String at) {
        super(name);
        assert !at.isEmpty() : "Empty at string.";
        this.at = at;
    }
    
    /**
     * Creates a new Event object with non-default isCompleted.
     *
     * @param name The name of the event.
     * @param isCompleted Whether the event is completed.
     * @param at Time of event.
     */
    public Event(String name, boolean isCompleted, String at) {
        super(name, isCompleted);
        assert !at.isEmpty() : "Empty at string.";
        this.at = at;
    }
    
    ////////////////////////////// PRINTING TASK
    
    public String getTimeString() {
        return StringParser.addRoundBracket("at: " + at);
    }
    
    @Override
    protected String getIdentifier() {
        return IDENTIFIER;
    }
    
    @Override
    protected String getIdentifierIcon() {
        return StringParser.addSquareBracket(getIdentifier());
    }
    
    @Override
    public String format() {
        return StringParser
                .combineTextWithPipe(
                        getIdentifier(),
                        getStatusInBinary(),
                        getName(),
                        at);
    }
    
    @Override
    public String toString() {
        return StringParser
                .combineTextWithSpace(
                        getIdentifierIcon(),
                        getStatusIcon(),
                        getName(),
                        getTimeString());
    }
}
