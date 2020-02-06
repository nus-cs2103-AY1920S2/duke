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
     * @param storage Object of type Storage.
     */
    public String execute(TaskList tasks, Storage storage) {
        int num = Integer.valueOf(str);
        String s = tasks.getList().get(num - 1).toString();
        tasks.getList().remove(num - 1);
        return "Noted. I've removed this task:\n" + s
                + "\nNow you have " + (tasks.getList().size()) + " tasks in the list.";
    }

    boolean isExit() {
        return false;
    }
}