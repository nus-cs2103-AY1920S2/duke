package commands;
import storage.Storage;
import task.Todo;
import taskList.TaskList;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final Todo td;

    /**
     * Convenience constructor using raw values.
     */
    public TodoCommand(String desc){
        this.td = new Todo(desc);
    }

    public TodoCommand(Todo td) {
        this.td = td;
    }

    public Todo getTodo() {
        return td;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.add(td);
        System.out.println("Successfully added: " + td);
        System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
        storage.save(tasks);
        //return new CommandResult(String.format("Successfully added:", dl));
    }
}
