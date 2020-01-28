public class newTaskAction implements Action {
    private String command;

    public newTaskAction(String command){
        this.command = command;
    }

    public void doSomething(TaskList tasks) {
        try {
            String taskDescription = GlobalScanner.sc.nextLine();
            Task currentTask = TaskHandler.taskHandler(command, taskDescription);
            tasks.add(currentTask);
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    public boolean hasNextAction() {
        return true;
    }
}
