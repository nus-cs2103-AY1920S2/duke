import java.util.ArrayList;

/**
 * TaskList class holds an ArrayList of all the tasks.
 */
public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<String> stringArr;

    /**
     * Constructor. Initialises the task list.
     *
     * @param stringArr stringArr is taken in as a parameter for the method parseTaskList.
     */
    TaskList(ArrayList<String> stringArr) {
        this.stringArr = stringArr;
        if (!stringArr.isEmpty()) {
            parseTaskList(stringArr);
        }
    }

    /**
     * This method converts each line in the text file to a Task object.
     *
     * @param stringArr Takes in an array of Strings in a certain format which is then converted to
     *                  individual Task objects.
     */
    private void parseTaskList(ArrayList<String> stringArr) {
        for (String st : stringArr) {
            String[] split = st.split(",");
            for (int i = 0; i < split.length; i++) {
                String currString = split[i];
                split[i] = currString.trim();
            }
            switch (split[0]) {
            case "E":
                taskList.add(new Event(split[2], split[3], split[1]));
                break;
            case "D":
                taskList.add(new Deadline(split[2], split[3], split[1]));
                break;
            case "T":
                taskList.add(new ToDos(split[2], split[1]));
                break;
            default:
                break;
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

}
