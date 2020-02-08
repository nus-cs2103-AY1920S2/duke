public class doneAction implements Action {
    private int index;

    public doneAction(int index) {
        this.index = index;
    }

    /**
     * Marks a task in the taskList as done given by the index of the next int from the GlobalScanner
     *
     * @param tasks taskList to mark done from
     */
    @Override
    public String doSomething(TaskList tasks) {
        if (index < 0) {
            return "Invalid Index found";
        }
        try {
            return tasks.done(index);
        } catch (InvalidIndexException e) {
            return e.toString();
        }
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
