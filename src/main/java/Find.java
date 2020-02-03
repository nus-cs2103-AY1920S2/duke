public class Find extends Command {
    String str;

    Find(String str) {
        super();
        this.str = str;
    }

    /**
     * It executes the find command.
     *
     * @param tasks Object of type TaskList.
     * @param ui Object of task Ui.
     * @param storage Object of type Storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String s = "Here are the matching tasks in your list:";
        int foundcount = 0;
        if (tasks.getList().size() > 0) {
            for (int i = 0; i < tasks.getList().size(); i++) {
                if (tasks.getList().get(i).getTaskName().contains(str)) {
                    s = s + (i + 1) + "." + tasks.getList().get(i);
                    foundcount++;
                }
            }
            if (foundcount == 0) {
                s = s + "Nothing Available with : " + str;
            }
        } else {
            s = s + "NoTasks available";
        }
        return s;
    }

    boolean isExit() {
        return false;
    }
}
