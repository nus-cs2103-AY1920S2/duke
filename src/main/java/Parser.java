public class Parser {
    TaskList taskList;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes in user String input, parses, and executes command accordingly.
     * @param in User string input
     */
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

    String getOutputString(String in) {
        String out;
        if (in.equals("bye")) {
            out = "Bye. Hope to see you again soon!";
        } else if (in.equals("list")) {
            out = taskList.getListString();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
                out = taskList.getDoneString(in);
            } else if (taskType.equals("delete")) {
                out = taskList.getDeleteString(in);
            } else if (Task.isValidTask(taskType)) {
                out = taskList.getAddString(in);
            } else if (in.isEmpty() || in == null) {
                out = "☹ OOPS!!! Please type something here.";
            } else {
                out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        return out;
    }
}
