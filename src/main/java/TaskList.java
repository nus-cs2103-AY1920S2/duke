import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> task_list;

    public TaskList() {
        task_list = new ArrayList<>(100);
    }

    public ArrayList<Task> getList() {
        return task_list;
    }

    public void AddSaveStringAsTask(String taskString) {
        // Takes in a string representation of a task and adds to list
        String task_info;
        String[] sep;
        switch (taskString.charAt(0)) {
            case 'T':
                task_list.add(new ToDo(taskString.substring(2, taskString.length())));
                break;
            case 'D':
                task_info = taskString.substring(2, taskString.length());
                sep = task_info.split("@");
                task_list.add(new Deadline(sep[0], LocalDateTime.parse(sep[1])));
                break;
            case 'E':
                task_info = taskString.substring(2, taskString.length());
                sep = task_info.split("@");
                task_list.add(new Event(sep[0], LocalDateTime.parse(sep[1])));
                break;
            default:
                break;
        }
        if (taskString.charAt(1) == '1')
            task_list.get(task_list.size() - 1).MarkAsDone();
    }

    public Task AddTask(String newTask, Task.TaskType taskType) {
        String[] str_arr;
        String task_name;
        try {
            switch (taskType) {
                case TODO:
                    if (newTask.isBlank())
                        throw new DukeException.EmptyToDo();
                    task_list.add(new ToDo(newTask));
                    break;
                case DEADLINE:
                    // newTask string consists of "<actual task name> /by <deadline>"
                    str_arr = newTask.split("/by");
                    task_name = str_arr[0].trim();
                    if (task_name.isBlank())
                        throw new DukeException.EmptyDeadlineName();
                    String deadline;
                    try {
                        deadline = str_arr[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // This will occur when user did not use a /by command
                        throw new DukeException.NoDeadlineTime();
                    }
                    // /by was used but is followed by blank
                    if (deadline.isBlank())
                        throw new DukeException.NoDeadlineTime();
                    task_list.add(new Deadline(task_name, deadline));
                    break;
                case EVENT:
                    // newTask string consists of "<actual task name> /at <datetime>"
                    str_arr = newTask.split("/at");
                    task_name = str_arr[0].trim();
                    if (task_name.isBlank())
                        throw new DukeException.EmptyEvent();
                    String eventTime;
                    try {
                        eventTime = str_arr[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // This will occur when user did not use a /at command
                        throw new DukeException.NoEventDatetime();
                    }
                    // /at was used but is followed by blank
                    if (eventTime.isBlank())
                        throw new DukeException.NoEventDatetime();
                    task_list.add(new Event(task_name, eventTime));
                    break;
                default:
                    break;
            }
            Ui.ShowTaskAdded(task_list.get(task_list.size() - 1), task_list.size());
            return task_list.get(task_list.size() - 1);
        } catch (DukeException.EmptyToDo | DukeException.EmptyDeadlineName | DukeException.NoDeadlineTime | DukeException.EmptyEvent | DukeException.NoEventDatetime e) {
            // currently all exceptions are handled just by relaying a message. Nothing special, yet.
            Ui.ShowError(e);
            return null;
        }
    }

    // DoneTask() returns true if task was found (set to done), else false
    public boolean DoneTask(int task_index) {
        if (task_index < task_list.size() && task_index >= 0) {
            task_list.get(task_index).MarkAsDone();
            Ui.ShowTaskDone(task_list.get(task_index));
            return true;
        } else {
            // Task does not exist
            Ui.ShowTaskNotFound();
            return false;
        }
    }

    // DeleteTask() returns true if task was found (then deleted), else false
    public boolean DeleteTask(int task_index) {
        if (task_index < task_list.size() && task_index >= 0) {
            String TaskToRemove = task_list.get(task_index).toString();
            task_list.remove(task_index);
            Ui.ShowTaskDelete(TaskToRemove, task_list.size());
            return true;
        } else {
            // Task does not exist
            Ui.ShowTaskNotFound();
            return false;
        }
    }
}
