package Togepi.Tasks;

import Togepi.DukeException;
import Togepi.Storage;
import Togepi.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/** Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int counter;
    private ArrayList<Task> todoList = Storage.getTodoList();
    private ArrayList<Task> deadlineList = Storage.getDeadlineList();
    private ArrayList<Task> eventList = Storage.getEventList();
    private ArrayList<Task> doneList = Storage.getDoneList();

    /** Creates a list of tasks which takes in a list containing
     *     all the tasks that have been added or were already in the list
     *     and a counter that keeps track of the total number of tasks in the list.
     * @param tasks represents the list of tasks
     * @param counter represents the total number of tasks in the list.
     */
    public TaskList(ArrayList<Task> tasks, int counter) {
        this.tasks = tasks;
        this.counter = counter;
    }

//    /** Gets the list of tasks
//     * @return an arraylist of the tasks.
//     */
//    public ArrayList<Togepi.Tasks.Task> getTasks() {
//        return tasks;
//    }

    /** Adds a task of type 'Togepi.Tasks.Todo' to the list of tasks.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'todo' and a description of the task
     */
    public String addTodo(String nextString, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (nextString.equals("todo")) {
            throw new DukeException(ui.showErrorMsg());
        }
        String name = nextString.substring(5);
        Task task = new Todo(name);

        todoList.add(task);

        tasks.add(counter, task);
        counter++;
        storage.save(tasks);
        return ui.showAddTaskMsg() + "\t" + task.toString() + "\n" + ui.showCounterMsg(counter);
    }

    /** Adds a task of type 'Togepi.Tasks.Deadline' to the list of tasks.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'deadline', a description of the task and the deadline
     *     of the task.
     */
    public String addDeadline(String nextString, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (nextString.equals("deadline") || !nextString.contains("/by")) {
            throw new DukeException(ui.showErrorMsg());
        }

        String name = nextString.substring(9);
        String taskName = name.split(" /by ")[0];
        String dateTime = name.split(" /by ")[1];
        String[] tempDate = dateTime.split(" ");
        int day = Integer.parseInt(tempDate[0].split("/")[0]);
        int month = Integer.parseInt(tempDate[0].split("/")[1]);
        int year = Integer.parseInt(tempDate[0].split("/")[2]);
        LocalDate date = LocalDate.of(year, month, day);
        Task task = new Deadline(taskName, date);

        deadlineList.add(task);

        tasks.add(counter, task);
        counter++;

        storage.save(tasks);

        return ui.showAddTaskMsg() + "\t" + task.toString() + "\n" + ui.showCounterMsg(counter);
    }

    /** Adds a task of type 'Togepi.Tasks.Event' to the list of tasks.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'event', a description of the task and the date
     *     of the task.
     */
    public String addEvent(String nextString, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (nextString.equals("event") || !nextString.contains("/at")) {
            throw new DukeException(ui.showErrorMsg());
        }

        String name = nextString.substring(5);

        String taskName = name.split(" /at ")[0];
        String dateTime = name.split(" /at ")[1];

        String[] tempDate = dateTime.split(" ");
        int day = Integer.parseInt(tempDate[0].split("/")[0]);
        int month = Integer.parseInt(tempDate[0].split("/")[1]);
        int year = Integer.parseInt(tempDate[0].split("/")[2]);
        LocalDate date = LocalDate.of(year, month, day);
        Task task = new Event(taskName, date);

        eventList.add(task);

        tasks.add(counter, task);
        counter++;
        storage.save(tasks);

        return ui.showAddTaskMsg() + "\t" + task.toString() + "\n" + ui.showCounterMsg(counter);
    }

    /** Deletes a task from the list of tasks, given the index of a task.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'delete' and the index of the task to be deleted.
     */
    public String deleteTask(String nextString, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (nextString.equals("delete")) {
            throw new DukeException(ui.showErrorMsg());
        }
        String[] temp = nextString.split(" ");
        int index = Integer.parseInt(temp[1]) - 1;
        Task current = tasks.get(index);

        if (current instanceof Todo) {
            storage.getTodoList().remove(current);
        } else if (current instanceof Deadline) {
            storage.getDeadlineList().remove(current);
        } else if (current instanceof Event) {
            storage.getEventList().remove(current);
        }

        tasks.remove(index);
        counter--;

        storage.save(tasks);
        return ui.showRemoveTaskMsg() + current.toString() + "\n" + ui.showCounterMsg(counter);
    }

    /** Marks a task in the list of tasks as complete, given the index of the task.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'done' and the index of the task.
     */
    public String markComplete(String nextString, Storage storage) {
        Ui ui = new Ui();

        String[] temp = nextString.split(" ");
        int index = Integer.parseInt(temp[1]) - 1;
        Task current = tasks.get(index);
        current.setIsDone();

        storage.save(tasks);
        return ui.showDoneMsg() + current.toString();
    }

    /** Lists all the tasks in the list of tasks that contains the keyword
     *     as inputted by the user.
     * @param nextString is the command entered by the user. Must contain
     *     the keyword 'find' and the keyword that the user wishes to filter
     *     the list of tasks against.
     */
    public String findTask(String nextString) {
        Ui ui = new Ui();
        String[] temp = nextString.split(" ");
        String keyword = temp[1];
        String toPrint = ui.showFindTaskMsg();
        int counter = 0;
        for (Task t: tasks) {
            if (t.getDescription().contains(keyword)) {
                toPrint = toPrint + (tasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
                counter++;
            }
        }
        assert counter > 0 : "You can't find a task on the list if it's not even on it!";
        return toPrint;
    }

    /** Lists all the tasks in the list of tasks.
     * @return a string message that shows the list of tasks
     */
    public String listTasks() {
        Ui ui = new Ui();
        String temp = ui.showListMsg();
        for (Task t : tasks) {
            temp = temp + (tasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
        }
        return temp;
    }

    /** Lists all the tasks of a specific type.
     * @param input contains the command and the type of task.
     * @return a strig that shows the list of filtered tasks.
     */
    public String filterType(String input) {
        Ui ui = new Ui();
        String type = input.split(" ")[1];
        String toPrint = ui.showFindTaskMsg();

        if (type.equals("todo")) {
            for (Task t: todoList) {
                toPrint = toPrint + (tasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
            }
        } else if (type.equals("deadline")) {
            for (Task t: deadlineList) {
                toPrint = toPrint + (tasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
            }
        } else if (type.equals("event")) {
            for (Task t: eventList) {
                toPrint = toPrint + (tasks.indexOf(t) + 1) + ". " + t.toString() + "\n";
            }
        }

        assert counter > 0 : "You can't find a task on the list if it's not even on it!";
        return toPrint;
    }

    /** Calculates the percentage of the tasks of a specific type.
     * @param input represents a string that contains the command and the type of task.
     * @return a string that shows the percentage of the tasks of that type.
     */
    public String calculatePercent(String input) {
        double num;
        double totalNum = tasks.size();
        String statsFor = input.split(" ")[1];
        Ui ui = new Ui();

        if (statsFor.equals("todo")) {
            num = todoList.size();
            return "Percentage of tasks that are Todos is: " + (num / totalNum);
        } else if (statsFor.equals("deadline")) {
            num = deadlineList.size();
            return "Percentage of tasks that are Deadlines is: " + (num / totalNum);
        } else if (statsFor.equals("event")) {
            num = eventList.size();
            return "Percentage of tasks that are Events is: " + (num / totalNum);
        } else if (statsFor.equals("done")) {
            num = doneList.size();
            return "Percentage of tasks that are Done is: " + (num / totalNum);
        }
        return ui.showErrorMsg();
    }
}
