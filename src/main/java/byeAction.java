public class byeAction implements Action {
    /**
     * Just does nothing
     * @param tasks
     */
    public void doSomething(TaskList tasks) {

    }

    /**
     * Returns a boolean determining whether there's a next action. In all cases except byeAction the boolean
     * returned is true
     * @return
     */
    public boolean hasNextAction() {
        return false;
    }
}
