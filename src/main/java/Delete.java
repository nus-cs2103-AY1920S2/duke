public class Delete extends Command {
    String str;

    Delete(String str) {
        super();
        this.str = str;
    }

    /**
     * It executes the delete command.
     *
     * @param tasks Object of type TaskList.
     * @param ui Object of task Ui.
     * @param storage Object of type Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num = Integer.valueOf(str);
        ui.printString("Noted. I've removed this task:");
        ui.printTask(num,tasks);
        ui.printString("Now you have " + (tasks.getList().size() - 1) + " tasks in the list.");
        tasks.list.remove(num - 1);
    }

    boolean isExit() {
        return false;
    }
}