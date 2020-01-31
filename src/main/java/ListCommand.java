/**
 * A command to list out the tasks stored in Duke.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:\n";
        int counter = 1;
        for (Task t : tasks.getTasks()) {
            response += (counter + "."); // Index of task
            response += (" " + t + '\n'); // Description of task and whether it is done
            counter++;
        }
        response = response.substring(0, response.length() - 1);
        ui.showMsg(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
