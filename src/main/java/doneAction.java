public class doneAction implements Action {
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
