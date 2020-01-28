public class deleteAction implements Action {
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
