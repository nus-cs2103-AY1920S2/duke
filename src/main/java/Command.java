public class Command {
    private String name;
    private String[] arguments;

    public Command(String input) {
        if (input.contains(" ")) {
            String[] inputs = input.split(" ", 2);
            this.name = inputs[0];
            if (inputs[1].contains(" ")) {
                this.arguments = inputs[1].split(" ");
            } else {
                this.arguments = new String[] {inputs[1]};
            }
        } else {
            this.name = input;
            this.arguments = new String[0];
        }
    }

    public String getCommandName() {
        return name;
    }

    public String[] getArgumentList() {
        return arguments;
    }

    public String getArgumentString() {
        return String.join(" ", arguments);
    }

    public String execute(TaskList tasks) throws DukeException {
        switch (getCommandName()) {
        case "": {
            throw new DukeNoCommandException();
        }
        case "todo": 
        case "deadline": 
        case "event": {
            Task newTask = createTask();
            return tasks.addTask(newTask);
        }
        case "list": {
            return tasks.toString();
        }
        case "done": {
            return markTask(tasks);
        }
        case "delete": {
            return deleteTask(tasks);
        }
        case "bye": {
            return "Goodbye!";
        }
        default: {
            break;
        }
        }
        throw new DukeUnrecognisedCommandException(getCommandName());
    }

    private Task createTask() throws DukeException {
        String taskType = getCommandName();
        if (getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(taskType);
        }
        switch (taskType) {
        case "todo": {
            return new TodoTask(getArgumentString());
        }
        case "deadline": {
            String[] parts = getArgumentString().split(" /by ", 2);
            if (parts.length != 2) {
                throw new DukeInvalidNumberOfArgumentsException(taskType, 2, parts.length);
            }
            return new DeadlineTask(parts[0], parts[1]);
        }
        case "event": {
            String[] parts = getArgumentString().split(" /at ", 2);
            if (parts.length != 2) {
                throw new DukeInvalidNumberOfArgumentsException(taskType, 2, parts.length);
            }
            return new EventTask(parts[0], parts[1]);
        }
        default: {
            throw new DukeUnrecognisedTaskTypeException(taskType);
        }
        }
    }

    private String markTask(TaskList tasks) throws DukeException {
        if (getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(getCommandName());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(getArgumentList()[0]) - 1;
            Task task = tasks.getTask(taskIndex);
            return task.markAsCompleted();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(getArgumentList()[0]);
        }
    }

    private String deleteTask(TaskList tasks) throws DukeException {
        if (getArgumentList().length == 0) {
            throw new DukeNoArgumentsException(getCommandName());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(getArgumentList()[0]) - 1;
            return tasks.removeTask(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(getArgumentList()[0]);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(getCommandName());
        if (arguments.length > 0) {
            output.append(String.format(" %s", getArgumentString()));
        }
        return output.toString();
    }
}


