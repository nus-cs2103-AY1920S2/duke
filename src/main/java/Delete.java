public class Delete extends Command {
    int num;

    Delete(int num) {
        super();
        this.num = num;
    }

    /**
     * It executes the delete command.
     *
     * @param tasks Object of type TaskList.
     * @param storage Object of type Storage.
     */
    public String execute(TaskList tasks, Storage storage) {
        assert num <= tasks.getList().size() && num >= 0 : "This task number does not exist in the list. "
                + "Please try again!";
        String s = tasks.getList().get(num - 1).toString();
        tasks.getList().remove(num - 1);
        return "Noted. I've removed this task:\n" + s
                + "\nNow you have " + (tasks.getList().size()) + " tasks in the list.";
    }

    boolean isExit() {
        return false;
    }
}