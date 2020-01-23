import java.util.ArrayList;

public class TaskManagement {

    ArrayList<Task> taskArr = new ArrayList<Task>();

    public void list() {

        String start = "\nHere are the tasks in your list:";
        System.out.println(start);

        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println((i + 1) + "." + taskArr.get(i).toString());
        }

    }

    public Task addTask(String taskDescription, String date, Task.Types type) {

        System.out.println("Got it. I've added this task:");
        switch (type) {
            case ToDo:
                ToDo task1 = new ToDo(Task.totalTask++, date, taskDescription);
                taskArr.add(task1);
                return task1;
            case Deadline:
                Deadline task2 = new Deadline(Task.totalTask++, date, taskDescription);
                taskArr.add(task2);
                return task2;
            case Event:
                Event task3 = new Event(Task.totalTask++, date, taskDescription);
                taskArr.add(task3);
                return task3;
            default:
                System.out.println("Task type does'nt exist");
        }
        return null;
    }

    public String reportTotal() {
        return "Now you have " + Task.totalTask + " tasks in the list";
    }

    public Task getTask(int index) {
        return taskArr.get(index - 1);
    }

    public void markDone(Task task) {
        task.changeStatus(true);
        String action = "Nice! I've marked this task as done:\n";
        String taskDescription = task.toString();
        System.out.println(action + taskDescription);
    }

}
