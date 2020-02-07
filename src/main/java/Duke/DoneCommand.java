package Duke;

public class DoneCommand extends Command {

    protected String done;
    public DoneCommand(String done) {
        this.done = done;
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        String num = getIndex();
        System.out.println("Nice! I've marked this task as done:\n");
        tasklist.getTask(Integer.parseInt(num) - 1).markAsDone();
        System.out.println(num + ". " + "[" + taskList.getTask(Integer.parseInt(num) - 1).getStatusIcon() + "] " + tasklist.getTask(Integer.parseInt(num) - 1).getDescription());
    }

    public String getIndex() {
        return done;
    }
}
