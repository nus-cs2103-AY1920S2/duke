package duke;

public class DeadlineCommandMethod implements CommandMethod {
    public static final String NAME = "deadline";

    public String execute(Duke program, Command command) throws DukeException { 
        if (command.getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(DeadlineCommandMethod.NAME);
        }
        String[] parts = command.getArgumentString().split(" /by ", 2);
        if (parts.length != 2) {
            throw new DukeInvalidNumberOfArgumentsException(
                    DeadlineCommandMethod.NAME,2, parts.length
            );
        }
        Task newTask = new DeadlineTask(parts[0], parts[1]);
        TaskList tasks = program.getTaskList();
        return tasks.addTask(newTask);
    }
}
