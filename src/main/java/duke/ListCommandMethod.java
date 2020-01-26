package duke;

public class ListCommandMethod implements CommandMethod {
    public static final String NAME = "list";

    public String execute(Duke program, Command command) { 
        TaskList tasks = program.getTaskList();
        return tasks.toString();
    }
}