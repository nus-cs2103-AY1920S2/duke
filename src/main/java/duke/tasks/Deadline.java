package duke.tasks;

import duke.utils.StringParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The <code>Deadline</code> class extends from Task.
 *
 * <p>A <code>Deadline</code> object has a <code>name</code>, a <code>isCompleted</code> property
 * and a <code>by</code> property which represents the time of the event.
 *
 * @author Zhu Yijie
 */
public class Deadline extends Task {
    private static final String IDENTIFIER = "D";
    public static final String TIME_DEMARCATOR = "/by";
    
    private LocalDate by;
    
    /**
     * Creates a new Deadline object.
     *
     * @param name The name of the deadline.
     * @param by Time the deadline is due.
     */
    public Deadline(String name, String by) {
        super(name);
        assert !by.isEmpty() : "Empty by string.";
        this.by = LocalDate.parse(by);
    }
    
    /**
     * Creates a new Deadline object with non-default isCompleted.
     *
     * @param name The name of the deadline.
     * @param isCompleted Whether the deadline is completed.
     * @param by Time the deadline is due.
     */
    public Deadline(String name, boolean isCompleted, String by) {
        super(name, isCompleted);
        assert !by.isEmpty() : "Empty by string.";
        this.by = LocalDate.parse(by);
    }
    
    ////////////////////////////// PRINTING TASK
    
    public String getTimeString() {
        return StringParser.addRoundBracket("by: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(by));
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
                        by.toString());
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
