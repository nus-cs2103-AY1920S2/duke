package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Find command.
 */
public class FindCommand implements Command {


    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String result = "";

        String[] split = task.split(" ", 2);
        String taskToFind = split[1];
        ArrayList<Task> matchedTask = new ArrayList<>();

        for (int i = 0; i < taskList.getArraySize(); i++) {
            Task currTask = taskList.getTask(i + 1);
            String taskName = currTask.getItem();

            if (taskName.contains(taskToFind)) {
                matchedTask.add(currTask);
            }
        }

        if (matchedTask.isEmpty()) {
            result += ui.showNoTaskFoundMessage();
        } else {
            result += ui.showFoundResultsMessage() + "\n";
            for (int i = 0; i < matchedTask.size(); i++) {
                if (i != matchedTask.size() - 1) {
                    result += (i + 1) + ". " + matchedTask.get(i).toString() + "\n";
                } else {
                    result += (i + 1) + ". " + matchedTask.get(i).toString();
                }
            }
        }

        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
