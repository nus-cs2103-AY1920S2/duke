public class Parser {
    TaskList taskList;

    public Parser(){}

    public void setTaskList(TaskList taskList){
        this.taskList = taskList;
    }
    public void getInput(String in){
        if (in.equals("list")){
            taskList.printList();
        } else {
            String taskType = in.split(" ", 2)[0];
            if (taskType.equals("done")) {
                taskList.printDone(in);
            } else if (taskType.equals("delete")){
                taskList.deleteTask(in);
            } else if (Task.isValidTask(taskType)){
                taskList.addTask(in);
            } else if (in.isEmpty() || in == null){
                System.err.println("     ☹ OOPS!!! Please type something here.");
            } else {
                System.err.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
