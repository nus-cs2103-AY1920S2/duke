import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<String> stringArr;

    TaskList(ArrayList<String> stringArr) {
        this.stringArr = stringArr;
        if (!stringArr.isEmpty()) {
            parseTaskList(stringArr);
        }
    }

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

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void addToList(Task task) {
        taskList.add(task);
        UI.wrapper("I have added: " + task.getTaskName() + "\n"
                        + "You now have " + taskList.size() + " tasks");
    }

    public static void doneAction(int num) {
        Task currTask = taskList.get(num);
        UI.wrapper("I have marked " + currTask.getTaskName() + " as done.\n"
                        + "You now have " + taskList.size() + " tasks left");
        currTask.mark();
    }

    public static void removeAction(int num) {
        Task currTask = taskList.get(num);
        taskList.remove(num);
        UI.wrapper("I have removed" + currTask.getTaskName() + "\n"
                + "You now have " + taskList.size() + " tasks left");
    }



}
