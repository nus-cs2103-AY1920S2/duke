import java.time.format.DateTimeParseException;

public class Parser {
    TaskList taskList;
    Command command;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
        this.command = new Command(taskList);
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
        String in = input.trim();
        String out;
      
        assert this.taskList != null;
        if (in.equals("bye")) {
            out = this.command.exit();
        } else if (in.equals("list")) {
            out = this.command.getListString();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
                out = this.command.done(in);
            } else if (taskType.equals("delete")) {
                out = this.command.delete(in);
            } else if (Task.isValidTask(taskType)) {
                out = this.command.add(in);
            } else if (in.isEmpty() || in == null) {
                out = this.command.emptyInput();
            } else {
                out = this.command.unknownCommand();
            }
        }
        return out;
    }
}
