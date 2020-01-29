public interface TaskDispatch {

    public static Task dispatchTask(String[] commandArgs) throws IllegalCommandException {
        Task t = null;
        switch (commandArgs[0]) {
            case "event":
                t = Event.createTask(commandArgs);
                break;
            case "todo":
                t = Todo.createTask(commandArgs);
                break;
            case "deadline":
                t = Deadline.createTask(commandArgs);
                break;
            default:
                // Unrecognised command
                throw new IllegalCommandException();
        }
        return t;
    }
}
