public class listAction implements Action {
    /**
     * Lists all the tasks in the task list
     * @param tasks
     */
    public void doSomething(TaskList tasks) {
        tasks.list();
    }

    public boolean hasNextAction() {
        return true;
    }
}
