package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Undo {

    private ArrayList<String> allVersionTask;


    public Undo() {
        allVersionTask = new ArrayList<>();
    }

    /**
     * Undo the step.
     *
     * @param taskList the tasklist which will be reverted
     * @param data     the data which will reset the database
     * @throws DukeException the duke exception
     */
    public void undo(TaskList taskList, Storage data) throws DukeException {
        int total = this.allVersionTask.size();
        System.out.println(total);
        String prevVersion = allVersionTask.get(total - 2);
        allVersionTask.remove(total - 1);
        ArrayList<Task> prevTaskList = data.loadData(prevVersion);
        taskList.setTaskList(prevTaskList);
    }

    /**
     * This method is use to set the first version control.
     */
    public void startFresh() {
        allVersionTask.add("");
    }


    public void addVersion(String latestVersion) {
        allVersionTask.add(latestVersion);
    }

    public String getPrevVersion() {
        int size = allVersionTask.size();
        return allVersionTask.get(size - 1);
    }

    public int getSize() {
        return allVersionTask.size();
    }

}
