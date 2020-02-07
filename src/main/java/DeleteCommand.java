public class DeleteCommand extends Command {
    protected String done;
    public DeleteCommand(String done) {
        this.done = done;
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        String num = getIndex();
        System.out.println("Noted! I've removed this task:\n");
        System.out.println(num + ". " + "[" + tasklist.getTask(Integer.parseInt(num) - 1).getStatusIcon() + "] " + tasklist.getTask(Integer.parseInt(num) - 1).getDescription());
        tasklist.removeTask(Integer.parseInt(num) - 1);
        storage.store(tasklist.getEntireList());
        System.out.println("Now you have " + tasklist.getTaskListSize() + " tasks in the list");
    }

    public String getIndex() {
        return done;
    }
}
