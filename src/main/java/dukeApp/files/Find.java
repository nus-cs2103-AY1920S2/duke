package dukeApp.files;

import java.util.ArrayList;

public class Find {
    String statement;
    ArrayList<Task> aList;

    public Find(String statement, ArrayList<Task> aList) {
        this.statement = statement;
        this.aList = aList;
    }

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
