public class EmptyAction implements Action {
    public String doSomething(TaskList tasks) {
        return "I don't know what to do!";
    }

    @Override
    public boolean hasNextAction() {
        return true;
    }
}
