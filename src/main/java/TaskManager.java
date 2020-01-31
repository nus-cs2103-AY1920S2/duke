import java.util.ArrayList;
import java.util.Collection;

public class TaskManager {
    public static ArrayList<Task> list;

    public static void initializeArray(ArrayList<Task> currentList) {
        list = currentList;
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static void addTask(Task taskToAdd) {
        list.add(taskToAdd);
    }

    public static Task deleteTask(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        Task deletedTask = list.get(taskNumber - 1);
        list.remove(taskNumber - 1);
        return deletedTask;
    }

    public static void clearAll() {
        list.clear();
    }

    public static Task markAsDone(int taskNumber) {
        if (taskNumber > list.size()) {
            throw new DukeException("☹ OOPS!!! There is no such task.");
        }
        list.get(taskNumber - 1).setDone();
        return list.get(taskNumber - 1);
    }

    public static int getTotalTasks() {
        return list.size();
    }

    public static boolean isEmpty() {
        return list.isEmpty();
    }

    public static String printTotalTasks() {
        if (getTotalTasks() == 0) {
            return "\tNow you have no tasks in the list.";
        } else if (getTotalTasks() == 1) {
            return String.format("\tNow you have %d task in the list.", getTotalTasks());
        } else {
            return String.format("\tNow you have %d tasks in the list.", getTotalTasks());
        }
    }

    public static String printList() {
        String printedList = "";
        int taskNumber = 1;
        for (Task task : list) {
            printedList = printedList + "\n\t\t" + taskNumber + ". \t" + task;
            taskNumber++;
        }
        return printedList;
    }
}
