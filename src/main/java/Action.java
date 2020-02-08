public interface Action {
    String doSomething(TaskList tasks);

    boolean hasNextAction();
}
