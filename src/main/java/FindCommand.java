import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> reqTasks = tasks.find(this.keyword);
        String response = "";
        if (reqTasks.size() == 0) {
            response = "There are no matching tasks with the keyword: " + this.keyword;
        } else {
            response = "Here are the matching tasks in your list:\n";
            int counter = 1;
            for (Task t: reqTasks) {
                response += (counter + "."); // Index of task
                response += (" " + t + '\n'); // Description of task and whether it is done
                counter++;
            }
            response = response.substring(0, response.length() - 1);
        }
        ui.showMsg(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}