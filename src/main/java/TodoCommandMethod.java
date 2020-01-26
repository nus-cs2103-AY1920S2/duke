public class TodoCommandMethod implements CommandMethod {
    public static final String NAME = "todo";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(TodoCommandMethod.NAME);
        }
        Task newTask = new TodoTask(command.getArgumentString());
        TaskList tasks = program.getTaskList();
        return tasks.addTask(newTask);
    }
}
