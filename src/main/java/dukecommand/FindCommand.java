package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

import java.util.ArrayList;

/**
 * Represents a Find Command that searches all tasks description to find the ones that matches
 * the specified keyword
 */

public class FindCommand extends DukeCommand {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches iteratively in each task's description to find the keyword and prints out the list
     * of tasks that contains the keyword
     *
     * @param dl  DukeList from main Duke program
     * @param ds  DukeStorage from main Duke program
     * @param dui DukeUI from main Duke program
     */
    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        if (dl.isEmpty()) {
            dui.printCustomMessage("    There's no list for me to search from. Your list is empty!");
        } else {
            DukeList collectingDL = new DukeList();
            ArrayList<Task> searchSpace = dl.getListOfTasks();

            for (Task curr : searchSpace) {
                if (isKeyWordFoundInTask(keyword, curr)) {
                    collectingDL.addTask(curr);
                } else {
                }
            }

            if (collectingDL.isEmpty()) {
                dui.printCustomMessage("    There's no task description that matches your keyword!");
            } else {
                dui.printCustomMessage("    Here's the list of tasks that contains your keyword!");
                dui.printListOfTasks(collectingDL.getListForUI());
            }
        }
    }
    @Override
    public boolean getIsExit() {
        return false;
    }

    private boolean isKeyWordFoundInTask(String target, Task currTask) {
        String taskDesc = currTask.getTaskName();
        String[] taskDescArr = taskDesc.split(" ");
        boolean isFound = false;

        for(String desc : taskDescArr) {
            if(target.equals(desc)) {
                isFound = true;
            }
        }

        return isFound;
    }
}