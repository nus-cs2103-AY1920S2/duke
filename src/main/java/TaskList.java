import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<List<String>> savedTasks) {
        tasks = new ArrayList<>();
        for (List<String> savedTask : savedTasks) {
            String type = savedTask.get(0);
            Task taskObject;
            if (type.equals("D")) {
                taskObject = new Deadline(savedTask.get(2), savedTask.get(3));
            } else if (type.equals("E")) {
                taskObject = new Event(savedTask.get(2), savedTask.get(3));
            } else {
                // type equals ("T")
                taskObject = new Todo(savedTask.get(2));
            }
            tasks.add(taskObject);

            if (savedTask.get(1).equals("1")) {
                // That means task was initially done
                taskObject.markAsDone();
            }
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addDeadline(String args) {
        String[] descAndBy = args.split(" /by ");
        Deadline deadline = new Deadline(descAndBy[0], descAndBy[1]);
        tasks.add(deadline);

        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent( deadline.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    public void addEvent(String args) {
        String[] descAndAt = args.split(" /at ");
        Event event = new Event(descAndAt[0], descAndAt[1]);
        tasks.add(event);

        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent(event.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    public void addTodo(String args) {
        Todo todo = new Todo(args);
        tasks.add(todo);
        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent(todo.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void markTaskAsDone(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        task.markAsDone();

        Ui.printLine();
        Ui.indent("Excellent! You have completed this task: ");
        Ui.doubleIndent(task.toString());
        Ui.printLine();
    }

    public void deleteTask(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        tasks.remove(taskNo - 1);

        Ui.printLine();
        Ui.indent("Understood. I have now removed this task: ");
        Ui.doubleIndent(task.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    public boolean isFull() {
        return tasks.size() == 100;
    }

}
