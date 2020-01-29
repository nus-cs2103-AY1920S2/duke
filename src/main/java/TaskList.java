import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    public void complete(int index) throws InvalidListItemAelitaException, DuplicateMarkAelitaException {

        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }
        taskList.get(index).markAsDone();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public boolean remove(Task task) throws InvalidArgumentAelitaException {
        boolean done = taskList.remove(task);
        if (done) {
            Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
        }
        return done;

    }

    public Task remove(int index) throws InvalidListItemAelitaException, InvalidArgumentAelitaException {
        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }
        Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
        return taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void list(Ui ui) throws EmptyListAelitaException {
        if (taskList.size() == 0) {
            throw new EmptyListAelitaException();
        } else {
            ui.printResponse(Response.LIST);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("  " + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    public void list(LocalDate date, Ui ui) {
        boolean hasTask = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline && ((Deadline) taskList.get(i)).by.equals(date)) {
                if (!hasTask) {
                    ui.printResponse(Response.LIST);
                }
                ui.printTask(taskList.get(i), i + 1);
                hasTask = true;
            } else if (taskList.get(i) instanceof Event && ((Event) taskList.get(i)).date.equals(date)) {
                if (!hasTask) {
                    ui.printResponse(Response.LIST);
                }
                ui.printTask(taskList.get(i), i + 1);
                hasTask = true;
            }
        }
        if (!hasTask) {
            ui.printResponse(Response.NO_TASK_ON_DATE);
        }
    }
}
