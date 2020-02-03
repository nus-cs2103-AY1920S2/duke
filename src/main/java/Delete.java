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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int num = Integer.valueOf(str);
        tasks.list.remove(num - 1);
        return "Noted. I've removed this task:\n"+ ui.printTask(num,tasks)
                + "\nNow you have " + (tasks.getList().size()) + " tasks in the list.";
    }

    boolean isExit() {
        return false;
    }
}