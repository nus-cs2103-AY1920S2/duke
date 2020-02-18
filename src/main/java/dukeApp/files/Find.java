package dukeApp.files;

import java.util.ArrayList;

public class Find {
    String statement;
    ArrayList<Task> aList;

    public Find(String statement, ArrayList<Task> aList) {
        this.statement = statement;
        this.aList = aList;
    }

    /**
     * Look through the task list to find task with matching keywords
     * @return the list of tasks with matching keywords
     */
    public ArrayList<Task> match() {
        ArrayList<Task> matchList = new ArrayList<>();

        for (Task task : aList) {
            String des = task.getDescription();
            if (des.matches("(.*)"+statement+"(.*)")) {
                matchList.add(task);
            }
        }
        return matchList;
    }
}
