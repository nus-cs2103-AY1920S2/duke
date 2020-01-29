
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;
    Storage taskStorage;

    public TaskList(Storage taskStorage) {

        this.tasks = new ArrayList<Task>();
        this.taskStorage = taskStorage;
        ArrayList<String> tasklist = taskStorage.startupStorage();
        createStartUpList(tasklist);

    }


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

    public ArrayList<Task> getList() {
        return this.tasks;
    }


    public void addTaskToList(Task newTask) {
        tasks.add(newTask);
    }

    public Task removeTaskFromList(int indexToRemove) {

        Task removed = tasks.remove(indexToRemove);
        return removed;

    }

    public int getSize() {

        return tasks.size();

    }

    public Task getTask(int index) {
        return tasks.get(index);
    }


}
