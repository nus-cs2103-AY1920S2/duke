package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;
import duke.tasks.Todo;

/**
 *  The <code>TodoCommand</code> class represents a todo command which creates a
 *  new Todo object and adds it to the task list.
 */
public class TodoCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Added:\n";
    public static final String MESSAGE_EMPTY_CONTENT =
            "Todo name not specified! Please enter in the format: todo [name]";
    public static final String MESSAGE_DUPLICATE_TODO =
            "Duplicate task not added:\n";
    
    private final String name;
    
    public TodoCommand(String content) {
        this.name = content;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        if (name.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_CONTENT);
        }
        
        Todo newTodo = new Todo(name);
        
        try {
            taskList.add(newTodo);
            return MESSAGE_SUCCESS + newTodo;
        } catch (IllegalArgumentException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO + newTodo);
        }
    }
}
