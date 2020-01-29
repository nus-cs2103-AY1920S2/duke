import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A List that stores all Task created.
 */
public class TaskList {

    ArrayList<Task> tasks;
    Storage taskStorage;

    /**
     * To instantiate the task list from storage, loading all tasks that are currently in the data.txt.
     * @param taskStorage storage where the copy of the task file is stored.
     */
    public TaskList(Storage taskStorage) {

        this.tasks = new ArrayList<Task>();
        this.taskStorage = taskStorage;
        ArrayList<String> tasklist = taskStorage.startupStorage();
        createStartUpList(tasklist);

    }


    /**
     * Method invoked to create the initial start-up list.
     * @param tasklist list that is loaded with the tasks from data.txt from the file.
     */
    private void createStartUpList(ArrayList<String> tasklist) {

        for (String task : tasklist) {

            String[] tasksArr = task.split("\\|");
            String taskType = tasksArr[0].trim();
            String taskCompleted = tasksArr[1].trim();
            String taskAction = tasksArr[2].trim() + " ";

            if (taskType.equals("T")) {

                Task newTask = new Todo(taskAction);
                if (taskCompleted.equals("1")) {
                    newTask.completedTask();
                }
                this.tasks.add(newTask);

            } else if (taskType.equals("D")) {

                String deadline = tasksArr[3];
                String date = deadline.split("  ")[0].trim();
                String time = deadline.split("  ")[1].trim();
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM d yyyy");
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm a");
                LocalDate standardDateFormat = LocalDate.parse(date, formatterDate);
                LocalTime standardTimeFormat = LocalTime.parse(time, formatterTime);
                String dateAndTime = standardDateFormat + " " + standardTimeFormat;

                Task newTask = new Deadline(taskAction, "by " + dateAndTime.replace(":", ""));


                if (taskCompleted.equals("1")) {
                    newTask.completedTask();
                }

                this.tasks.add(newTask);

            } else if (taskType.equals("E")) {

                String eventTiming = tasksArr[3];
                Task newTask = new Event(taskAction, "at" + eventTiming);

                if (taskCompleted.equals("1")) {
                    newTask.completedTask();
                }

                this.tasks.add(newTask);


            }
        }


    }

    /**
     * Returns the task list.
     * @return the task list.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Remove a particular task from the task list
     * @param indexToRemove the index of which the task is to be removed.
     * @return the removed task.
     */
    public Task removeTaskFromList(int indexToRemove) {

        Task removed = tasks.remove(indexToRemove);
        return removed;

    }

    /**
     * Returns the total number of task in the list.
     * @return the total number of task in the list.
     */
    public int getSize() {

        return tasks.size();

    }

    /**
     * Returns the list at the index specified.
     * @param index index of the task in the list.
     * @return the specified task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }


}
