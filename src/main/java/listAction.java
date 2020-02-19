public class listAction implements Action {
    /**
     * Lists all the tasks in the task list
     *
     * @param tasks
     */
    public String doSomething(TaskList tasks) {
        return tasks.list();
    }

    public boolean hasNextAction() {
        return true;
    }
}
