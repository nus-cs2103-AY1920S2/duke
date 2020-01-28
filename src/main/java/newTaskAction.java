public class newTaskAction implements Action {
    private String command;

    public newTaskAction(String command){
        this.command = command;
    }

    /**
     * Attempts to create a new Task if possible and add it to the taskList if there is one
     * @param tasks taskList to add Task to
     */
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
