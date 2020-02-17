import java.io.IOException;

public class byeAction implements Action {
    /**
     * Just does nothing
     *
     * @param tasks
     */
    public String doSomething(TaskList tasks) {
        try {
            Storage.storeIntoFile(tasks.getList());
        } catch (IOException e) {
            System.err.println(e);
        }
        return "Goodbye! Hope to see you soon!";
    }

    /**
     * Returns a boolean determining whether there's a next action. In all cases except byeAction the boolean
     * returned is true
     *
     * @return
     */
    public boolean hasNextAction() {
        return false;
    }
}
