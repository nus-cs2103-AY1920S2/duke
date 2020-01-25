public class UserInput {
    private String command;
    private String[] arguments;

    public UserInput(String input) {
        if (input.contains(" ")) {
            String[] inputs = input.split(" ", 2);
            this.command = inputs[0];
            if (inputs[1].contains(" ")) {
                this.arguments = inputs[1].split(" ");
            } else {
                this.arguments = new String[] {inputs[1]};
            }
        } else {
            this.command = input;
            this.arguments = new String[0];
        }
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getArgumentsAsString() {
        return String.join(" ", arguments);
    }

    public String execute(TaskList tasks) throws DukeException {
        switch (getCommand()) {
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
        throw new DukeUnrecognisedCommandException(getCommand());
    }

    private Task createTask() throws DukeException {
        String taskType = getCommand();
        if (getArguments().length == 0) {
            throw new DukeNoArgumentsException(taskType);
        }
        switch (taskType) {
        case "todo": {
            return new TodoTask(getArgumentsAsString());
        }
        case "deadline": {
            String[] parts = getArgumentsAsString().split(" /by ", 2);
            if (parts.length != 2) {
                throw new DukeInvalidNumberOfArgumentsException(taskType, 2, parts.length);
            }
            return new DeadlineTask(parts[0], parts[1]);
        }
        case "event": {
            String[] parts = getArgumentsAsString().split(" /at ", 2);
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
        if (getArguments().length == 0) {
            throw new DukeNoArgumentsException(getCommand());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(getArguments()[0]) - 1;
            Task task = tasks.getTask(taskIndex);
            return task.markAsCompleted();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(getArguments()[0]);
        }
    }

    private String deleteTask(TaskList tasks) throws DukeException {
        if (getArguments().length == 0) {
            throw new DukeNoArgumentsException(getCommand());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(getArguments()[0]) - 1;
            return tasks.removeTask(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(getArguments()[0]);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(command);
        if (arguments.length > 0) {
            output.append(" ");
            output.append(String.join(" ", arguments));
        }
        return output.toString();
    }
}