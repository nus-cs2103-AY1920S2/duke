public class doneAction implements Action {
    /**
     * Marks a task in the taskList as done given by the index of the next int from the GlobalScanner
     * @param tasks taskList to mark done from
     */
    @Override
    public void doSomething(TaskList tasks) {
        int index = GlobalScanner.sc.nextInt();
        try {
            tasks.done(index);
        } catch (InvalidIndexException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
