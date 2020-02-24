import java.util.ArrayList;

public class Find extends Command {
    String str;

    Find(String str) {
        super();
        this.str = str;
    }

    /**
     * It executes the find command by searching for every task that contains that keyword.
     *
     * @param tasks Object of type TaskList.
     * @param storage Object of type Storage.
     * @return Returns the list of tasks that contain user input keyword
     */
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        if (tasks.getList().size() > 0) {
            for (int i = 0; i < tasks.getList().size(); i++) {
                if (tasks.getList().get(i).getTaskName().contains(str)) {
                    s = s.append((i + 1) + "." + tasks.getList().get(i) + "\n");
                    count++;
                }
            }
            if (count == 0) {
                s = s.append("Nothing Available with : " + str + "\n");
            }
        } else {
            s = s.append("NoTasks available\n");
        }
        return s.toString();
    }
}
