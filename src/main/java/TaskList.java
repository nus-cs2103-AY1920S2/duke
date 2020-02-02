import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskList;

    public TaskList() {

        taskList = new ArrayList<>();

    }


    public static void addTask(Task task) {

        taskList.add(task);
    }

    public static void deleteTask(int index) {

        taskList.remove(index - 1);

    }

    public static Task getTask(int index) {

        return taskList.get(index);
    }

    public static int getSize() {

        return taskList.size();
    }

    public static void set(int index, Task task) {

        taskList.set(index, task);
    }


}
