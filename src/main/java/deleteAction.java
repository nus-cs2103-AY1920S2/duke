public class deleteAction implements Action {
    private int index;

    public deleteAction(int index) {
        this.index = index;
    }

    /**
     * For deleteAction doSomething deletes a task specified by an index from the next int of the global scanner
     * from the TaskList
     *
     * @param tasks the TaskList to delete from
     */
    public String doSomething(TaskList tasks) {
        if (index < 0) {
            return "Invalid Index found";
        }
        assert(index >= 0 && index <= tasks.getList().size());
        try {
            return tasks.delete(index);
        } catch (InvalidIndexException e) {
            return (e.toString());
        }
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
