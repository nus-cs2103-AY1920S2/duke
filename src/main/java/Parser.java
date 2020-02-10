import java.time.format.DateTimeParseException;

public class Parser {
    TaskList taskList;
//    Command command;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
//        this.command = new Command(taskList);
    }

    /**
     * Takes in user String input, parses, and executes command accordingly.
     * @param in User string input
     */
    /*
    public void getInput(String in) {
        if (in.equals("list")) {
            taskList.printList();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
                taskList.printDone(in);
            } else if (taskType.equals("delete")) {
                taskList.deleteTask(in);
            } else if (Task.isValidTask(taskType)) {
                taskList.addTask(in);
            } else if (in.isEmpty() || in == null) {
                System.err.println("     ☹ OOPS!!! Please type something here.");
            } else {
                System.err.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    */

    /**
     * Takes in user String input, parses, and executes command accordingly, and returns output string by Duke.
     * @param input User string input
     * @return Output string by Duke
     */
    String getOutputString(String input) {
        String in = input.trim().toLowerCase();
        String out;
      
        assert this.taskList != null;
        if (in.equals("bye")) {
//            out = this.command.exit();
            Command exitCommand = new ExitCommand();
            out = exitCommand.execute();
        } else if (in.equals("list")) {
//            out = this.command.getListString();
            Command listCommand = new ListCommand(taskList);
            out = listCommand.execute();
        } else if (in.equals("statistics") || in.equals("stats")) {
            Command statsCommand = new StatsCommand(taskList);
            out = statsCommand.execute();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
//                out = this.command.done(in);
                Command doneCommand = new DoneCommand(taskList, in);
                out = doneCommand.execute();
            } else if (taskType.equals("delete")) {
//                out = this.command.delete(in);
                Command deleteCommand = new DeleteCommand(taskList, in);
                out = deleteCommand.execute();
            } else if (Task.isValidTask(taskType)) {
//                out = this.command.add(in);
                Command addCommand = new AddCommand(taskList, in);
                out = addCommand.execute();
            } else if (in.isEmpty() || in == null) {
                Command emptyCommand = new EmptyCommand();
                out = emptyCommand.execute();
            } else {
                Command unknownCommand = new UnknownCommand();
                out = unknownCommand.execute();
            }
        }
        assert out != null;
        return out;
    }
}
