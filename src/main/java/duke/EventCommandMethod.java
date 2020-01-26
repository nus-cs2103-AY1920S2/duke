package duke;

public class EventCommandMethod implements CommandMethod {
    public static final String NAME = "event";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(EventCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /at ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    EventCommandMethod.NAME, 2, parts.length
            );
        }
        Task newTask = new EventTask(parts[0], parts[1]);
        TaskList tasks = program.getTaskList();
        return tasks.addTask(newTask);
    }
}
