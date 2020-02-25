import java.util.ArrayList;

/**
 * Represents a task list
 * @author kerwei
 * @version 1.0
 */
public class TaskList {
    public static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into task list
     * @param task Task
     */
    public static void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param index Index of task
     */
    public static void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the task at a specific index
     * @param index Index of task to be retrieved
     * @return Task at a specific index
     */
    public static Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the list of task containing keyword
     * @param keyword Keyword find in task
     */
    public static void find(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.contains(keyword)) {
                list.add(t);
            }
        }
        int i = 1;
        for (Task t : list) {
            System.out.println(i+ ". " + t);
        }
    }

    /**
     * Returns the number of task in task list
     * @return Size of task list
     */
    public static int getSize() {
        return taskList.size();
    }

    @Override
    public String toString (){
        String result = "";
        int i = 1;
        if (taskList.size() == 0) {
            result = "there is nothing in the list idiot";
        } else {
            for (Task t : taskList) {
                result += i + "." + t + "\n";
                i++;
            }
        }

        return result;
    }

}
