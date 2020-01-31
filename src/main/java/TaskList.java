import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<String> taskListString) {
        this.taskList = new ArrayList<Task>();
        for (String taskString: taskListString) {
            Task task = taskFromString(taskString);
            this.taskList.add(task);
        }
    }

    /**
     * Generates and returns a task given a task string.
     * Returns null if the task string is not in a valid format.
     * @param taskString a single line task in a String format read from file
     * @return Task generated from a TaskString
     */
    private Task taskFromString(String taskString) {
        Task taskToReturn = null;
        String[] strArr = taskString.split(" ");
        ArrayList<String> temp = new ArrayList<>();
        Collections.addAll(temp, strArr);
        char type = temp.get(0).charAt(1);
        //check if the char is Done symbol
        boolean isDone = temp.get(0).charAt(4) == '\u2713';

        switch (type){
        case 'T':
            // case for ToDo
            StringBuilder sbt = new StringBuilder();
            for (int i = 1; i < temp.size(); i++) {
                sbt.append(temp.get(i));
                if (i != (temp.size() - 1)) {
                    sbt.append(" ");
                }
            }
            String descT = sbt.toString();
            taskToReturn = new ToDo(descT);
            break;
        case 'D':
            // case for Deadline
            StringBuilder sbd = new StringBuilder();
            for (int i = 1; i < temp.size(); i++) {
                if (i != (temp.size() - 1)) {
                    String curr = temp.get(i);
                    if (curr.equals(":")) {
                        //if equals to colon, replace it with /, don't add space behind
                        sbd.append("\\");
                    } else {
                        sbd.append(curr);
                        sbd.append(" ");
                    }
                } else {
                    sbd.append(temp.get(i));
                }
            }
            String descD =  sbd.toString();
            taskToReturn = new Deadline(descD);
            break;
        case 'E':
            StringBuilder sbe = new StringBuilder();
            for (int i = 1; i < temp.size(); i++) {
                if (i != (temp.size() - 1))  {
                    String curr = temp.get(i);
                    if (curr.equals(":")) {
                        sbe.append("\\");
                    } else {
                        sbe.append("curr");
                        sbe.append(" ");
                    }
                } else {
                    sbe.append(temp.get(i));
                }
            }
            String descE = sbe.toString();
            taskToReturn = new Event(descE);
            break;
        default:
            break;

        }

        if (isDone) {
            taskToReturn.setDone();
        }
        return taskToReturn;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
