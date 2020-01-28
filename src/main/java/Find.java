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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the matching tasks in your list:");
        int foundcount = 0;
        if (tasks.getList().size() > 0) {
            for (int i = 0; i < tasks.getList().size(); i++) {
                if (tasks.getList().get(i).getTaskName().contains(str)) {
                    System.out.println((i + 1) + "." + tasks.getList().get(i));
                    foundcount++;
                }
            }
            if (foundcount == 0) {
                ui.printString("Nothing Available with : " + str);
            }
        } else {
            ui.printString("NoTasks available");
        }
    }

    boolean isExit() {
        return false;
    }
}
