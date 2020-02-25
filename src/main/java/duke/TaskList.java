package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the list which stores the tasks input by the user.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList(Ui ui) {
        this.taskList = new ArrayList<Task>();
        this.ui = ui;
    }

    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Delete a task from the list based on the index input by the user.
     *
     * @param index Index of a task in the list.
     * @throws DukeException If the index input is unavailable in the list of tasks.
     */
    public String delete(int index) {
        String output = "";
        try {
            if (index <= taskList.size() && index >= 1) {
                Task delTask = taskList.get(index - 1);
                taskList.remove(index - 1);
                output = ui.delete(delTask, taskList.size());
            } else {
                throw new DukeException("Invalid Task Index");
            }
        } catch (DukeException ex) {
            output = ui.showInvalidTaskIndex();
        } catch (NumberFormatException ex1) {
            output = ui.showInvalidTaskIndex();
        }

        return output;
    }

    /**
     * Changes the indicated task's status to <code>done</code>.
     *
     * @param index Index of a task in the list.
     * @throws DukeException If the index input is unavailable in the list of tasks.
     */
    public String done(int index) {
        String output = "";
        Task doneTask;
        try {
            if (index <= taskList.size() && index >= 1) {
                doneTask = taskList.get(index - 1);
            } else {
                throw new DukeException("Invalid Task Index");
            }

            if (doneTask.isDone()) {
                output = ui.showAlreadyDone(doneTask);
            } else {
                doneTask.done();
                output = ui.done(doneTask);
            }
        } catch (DukeException ex) {
            output = ui.showInvalidTaskIndex();
        } catch (NumberFormatException ex1) {
            output = ui.showInvalidTaskIndex();
        }
        return output;
    }

    /**
     * Changes the indicated task's status to <code>incomplete</code>.
     *
     * @param index Index of a task in the list.
     * @throws DukeException If the index input is unavailable in the list of tasks.
     */
    public String undone(int index) {
        String output = "";
        try {
            if (index <= taskList.size() && index >= 1) {
                Task doneTask = taskList.get(index - 1);
                doneTask.undone();
                output = ui.undone(doneTask);
            } else {
                throw new DukeException("Invalid Task Index");
            }
        } catch (DukeException ex) {
            output = ui.showInvalidTaskIndex();
        } catch (NumberFormatException ex1) {
            output = ui.showInvalidTaskIndex();
        }
        return output;
    }

    /**
     * Adds a new to-do into the tasks list.
     *
     * @param instr Description of the to-do.
     * @throws DukeException If the description of the to-do is empty.
     */
    public String addTodo(String instr) {
        String output = "";
        try {
            if (!instr.isEmpty()) {
                Todo newTask = new Todo(instr);
                taskList.add(newTask);
                output = ui.addTask(newTask, taskList.size());
            } else {
                throw new DukeException("Invalid Todo Description");
            }
        } catch (DukeException ex) {
            output = ui.showEmptyTodoDesc();
        }
        return output;
    }

    /**
     * Adds a new deadline into the tasks list.
     *
     * @param instr Description of the deadline.
     * @throws DukeException If the description of the deadline is empty.
     */
    public String addDeadline(String instr) {
        String output = "";
        try {
            if (!instr.isEmpty()) {
                String[] splitTime = instr.split(" /by ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                Deadline newTask = new Deadline(splitTime[0],
                        LocalDateTime.parse(splitTime[1], formatter));
                taskList.add(newTask);
                output = ui.addTask(newTask, taskList.size());
            } else {
                throw new DukeException("Invalid Deadline Description");
            }
        } catch (DukeException ex) {
            output = ui.showInvalidDeadlineDesc();
        } catch (ArrayIndexOutOfBoundsException ex1) {
            output = ui.showInvalidDeadlineDesc();
        }
        return output;
    }

    /**
     * Adds a new event into the tasks list.
     *
     * @param instr Description of the event.
     * @throws DukeException If the description of the event is empty.
     */
    public String addEvent(String instr) {
        String output = "";
        try {
            if (!instr.isEmpty()) {
                String[] splitTime = instr.split(" /at ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                Event newTask = new Event(splitTime[0],
                        LocalDateTime.parse(splitTime[1], formatter));
                taskList.add(newTask);
                output = ui.addTask(newTask, taskList.size());
            } else {
                throw new DukeException("Invalid Event Description");
            }
        } catch (DukeException ex) {
            output = ui.showInvalidEventDesc();
        } catch (ArrayIndexOutOfBoundsException ex1) {
            output = ui.showInvalidEventDesc();
        }
        return output;
    }

    /**
     * Returns a list of tasks that contains the <code>keyword</code>.
     *
     * @param keyword Search keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundList = new ArrayList<Task>();
        for (Task t:taskList) {
            if (t.getInstr().contains(keyword)) {
                foundList.add(t);
            }
        }
        return foundList;
    }

    /**
     * Sorts the task list in specific order.
     *
     * @param instr Description on how the list should be sorted.
     * @throws DukeException If the description of the sort is invalid.
     */
    public String sort(String instr) {
        String output = "";
        String desc = "";
        try {
            if (!instr.isEmpty()) {
                String[] splitBy = instr.split(" /by ");
                desc = splitBy[1];
            } else {
                throw new DukeException("Invalid Sort Description");
            }

            if (desc.equals("time")) {
                Collections.sort(taskList, new TaskComparatorByTime());
                return ui.list(this);
            } else if (desc.equals("task name")) {
                Collections.sort(taskList, new TaskComparatorByName());
                return ui.list(this);
            } else if (desc.equals("task type")) {
                Collections.sort(taskList, new TaskComparatorByType());
                return ui.list(this);
            } else {
                throw new DukeException("Invalid Sort Description");
            }
        } catch (DukeException ex) {
            output = ui.showInvalidSortDesc();
        } catch (ArrayIndexOutOfBoundsException ex1) {
            output = ui.showInvalidSortDesc();
        }
        return output;
    }

    /**
     * A getter for the list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += "  " + (i + 1) + ".  " + taskList.get(i).toString() + "\n";
        }
        return output;
    }
}
