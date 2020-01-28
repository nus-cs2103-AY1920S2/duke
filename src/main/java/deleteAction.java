public class deleteAction implements Action {
    /**
     * For deleteAction doSomething deletes a task specified by an index from the next int of the global scanner
     * from the TaskList
     * @param tasks the TaskList to delete from
     */
    public void doSomething(TaskList tasks) {
        int index = GlobalScanner.sc.nextInt();
        try {
            tasks.delete(index);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
