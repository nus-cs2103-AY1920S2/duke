package duke.task;

import java.util.ArrayList;

/**
 * The Class FindTask: to get the specific task out.
 * by checking the task description with the keywords.
 */
public class FindTask extends TaskList {

    /**
     * The specific task.
     */
    protected ArrayList<Task> specificTask;


    /**
     * Instantiates a new find task.
     *
     * @param allData the all data
     */
    public FindTask(ArrayList<Task> allData) {
        this.specificTask = allData;
    }

    /**
     * To find the specific task according to the keyword.
     *
     * @param keywords an string array contains all the keywords
     */
    public void search(String... keywords) {
        ArrayList<Task> targetList = new ArrayList<>();
        for (int i = 0; i < specificTask.size(); i++) {
            for (String word : keywords) {
                if (specificTask.get(i).getDescription().contains(word)) {
                    targetList.add(specificTask.get(i));
                    break;
                }
            }
        }
        this.specificTask = targetList;
    }

    /**
     * Gets the specific task.
     *
     * @return the specific task
     */
    public ArrayList<Task> getSpecificTask() {
        return this.specificTask;
    }

    /**
     * List.
     */
    @Override
    public String list() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        int taskSize = this.specificTask.size();
        for (int i = 0; i < taskSize; i++) {
            int count = i + 1;
            output.append("      " + count + ". " + this.specificTask.get(i).toString());
        }
        return output.toString();
    }
}
