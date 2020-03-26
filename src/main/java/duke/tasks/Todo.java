package duke.tasks;

import duke.utils.StringParser;

/**
 * The <code>Todo</code> class extends from Task.
 *
 * <p>A <code>Todo</code> object has a <code>name</code> and a <code>isCompleted</code> property.
 *
 * @author Zhu Yijie
 */
public class Todo extends Task {
    private static final String IDENTIFIER = "T";
    
    /**
     * Creates a new Todo object.
     *
     * @param name The name of the todo.
     */
    public Todo(String name) {
        super(name);
    }
    
    /**
     * Creates a new Todo object with non-default isCompleted.
     *
     * @param name The name of the todo.
     * @param isCompleted Whether the todo is completed.
     */
    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }
    
    ////////////////////////////// PRINTING TASK
    
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
                        getName());
    }
    
    @Override
    public String toString() {
        return StringParser
                .combineTextWithSpace(
                        getIdentifierIcon(),
                        getStatusIcon(),
                        getName());
    }
}
