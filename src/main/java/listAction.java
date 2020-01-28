public class listAction implements Action {

    public void doSomething(TaskList tasks) {
        tasks.list();
    }

    public boolean hasNextAction() {
        return true;
    }
}
