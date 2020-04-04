import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskList;



    /**
     * Constructor for TaskList class
     * Creates a new ArrayList: taskList, which is a static method
     * storing all the tasks that the user inputs
     *
     */

    public TaskList() {

        taskList = new ArrayList<>();

    }


    /**
     * Method adds the respective task into static ArrayList taskList
     *
     * @param task which is the task required to be added into taskList
     *
     */

    public static void addTask(Task task) {

        taskList.add(task);
    }

    /**
     * Method deletes the respective task in static ArrayList taskList
     *
     * @param index which is the index of the task in ArrayList taskList
     *              to be deleted
     *
     */

    public static void deleteTask(int index) {

        taskList.remove(index);

    }

    /**
     * Method returns the respective task in static ArrayList taskList
     *
     * @param index which is the index of the task in ArrayList taskList
     *              to be returned
     *
     */

    public static Task getTask(int index) {

        return taskList.get(index);
    }

    /**
     * Method returns the respective size of static ArrayList taskList
     *
     */

    public static int getSize() {

        return taskList.size();
    }

    /**
     * Method sets a respective task of static ArrayList taskList
     * and changes it accordingly
     *
     * @param index of the task in taskList to be changed
     * @param task is the task to replace the task of the param index
     *             to be changed
     *
     */

    public static void set(int index, Task task) {

        taskList.set(index, task);
    }


}
