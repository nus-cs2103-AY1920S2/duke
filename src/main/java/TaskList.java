import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Ui ui;
    private Storage storage;
    private List<Task> taskList;

    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        taskList = new ArrayList<>();
    }

    public void setList(List<Task> list) { taskList = list; }

    public void add(String s) throws DukeException {
        String typeOfTask = s.split(" ", 2)[0];
        Task toAdd = new Task();
        if (typeOfTask.equals("todo")) {
            try {
                String task = s.split(" ", 2)[1];
                toAdd = new Todo(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TodoException();
            }
        } else if (typeOfTask.equals("event")) {
            try {
                String task = s.split(" ", 2)[1];
                String[] taskParts = task.split(" /at ");
                toAdd = new Event(taskParts[0], taskParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EventException();
            }
        } else if (typeOfTask.equals("deadline")) {
            try {
                String task = s.split(" ", 2)[1];
                String[] taskParts = task.split(" /by ");
                toAdd = new Deadline(taskParts[0], taskParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DeadlineException();
            }
        }
        taskList.add(toAdd);
        ui.print("Gotcha! Added this task:\n"
                + "  " + toAdd + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            ui.showError(e);
        }
    }

    public void delete(int i) {
        Task t = taskList.remove(i-1);
        ui.print("Poof! This task is gone:\n"
                +  "  " + t + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            ui.showError(e);
        }
    }

    public void showList() {
        ui.print("Here are the tasks in your list:");
        int count = 1;
        for (Task t : taskList) {
            ui.print(count + "." + t);
            count++;
        }
    }

    public void done(int i) {
        taskList.get(i-1).markAsDone();
        ui.print("Nice! I've marked this task as done: \n" +
                "  " + taskList.get(i-1));
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            ui.showError(e);
        }
    }
}
