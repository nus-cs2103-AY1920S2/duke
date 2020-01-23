public class Parser {
    public Command parse(String command) {
        if (command.equals("bye")) {
            return Command.EXIT_DUKE;
        } else if (command.equals("list")) {
            return Command.LIST_TASKS;
        } else if (command.equals("done")) {
            return Command.MARK_TASK_AS_DONE;
        }
        return Command.ADD_TASK;
    }
}