package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * TaskList handles list of tasks in an ArrayList.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui = new Ui();

    /**
     * Constructor that takes in list of tasks.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to add task into task list.
     *
     * @param arr
     */
    public void add(String[] arr) {
        try {
            String taskType = arr[0];
            String[] temp;
            String task = "";
            checkDescription(arr.length);
            Task newTask = new Task("");

            switch (taskType) {
            case "todo":
                newTask = new ToDo(arr[1]);
                this.tasks.add(newTask);
                break;

            case "deadline":
                temp = arr[1].split("/by ");
                task = temp[0];
                String date = temp[1];
                try {
                    LocalDate localDate = LocalDate.parse(date);
                    newTask = new Deadline(task, localDate);
                    this.tasks.add(newTask);
                } catch (DateTimeParseException ex) {
                    ui.showDateError();
                }
                break;

            case "event":
                temp = arr[1].split("/at ");
                task = temp[0];
                String time = temp[1];
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(time);
                    newTask = new Event(task, localDateTime);
                    this.tasks.add(newTask);

                } catch (DateTimeParseException ex) {
                    ui.showDateTimeError();
                }
            }

            if (!newTask.description.equals("")) {
                ui.showTaskAdded(newTask, this.tasks);
            }

        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to delete task in task list.
     *
     * @param arr
     */
    public void delete(String[] arr) {
        try {
            checkNum(arr.length);
            checkValid(arr[1]);
            int taskNum = Integer.parseInt(arr[1]) - 1;

            if (this.tasks.size() > taskNum) {
                Task current = this.tasks.get(taskNum);
                this.tasks.remove(taskNum);
                ui.showTaskDeleted(current, this.tasks);

            } else {
                ui.showTaskError();
            }

        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to mark task as done.
     *
     * @param arr
     */
    public void done(String[] arr) {
        try {
            checkNum(arr.length);
            checkValid(arr[1]);
            int taskNum = Integer.parseInt(arr[1]) - 1;

            if (this.tasks.size() > taskNum) {
                Task current = this.tasks.get(taskNum);
                current.markDone();
                ui.showDone(current);

            } else {
                ui.showTaskError();
            }

        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to find tasks with matching keywords.
     *
     * @param arr
     */
    public void find(String[] arr) {
        String input = arr[1];
        ArrayList<Task> temp = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.description.contains(input)) {
                temp.add(task);
            }
        }
        ui.showFound(temp);
    }

    /**
     * Method to check if user has entered task description.
     *
     * @param size
     * @throws DukeException
     */
    public static void checkDescription(int size) throws DukeException {
        Ui ui = new Ui();

        if (size < 2) {
            throw new DukeException(ui.showDescriptionError());
        }
    }

    /**
     * Method to check if user has entered only 1 task number.
     *
     * @param input
     * @throws DukeException
     */
    public static void checkValid(String input) throws DukeException {
        String[] arr = input.split(" ");
        Ui ui = new Ui();

        if (arr.length > 1) {
            throw new DukeException(ui.showValidError());
        }
    }

    /**
     * Method to check if user has entered a task number.
     *
     * @param size
     * @throws DukeException
     */
    public static void checkNum(int size) throws DukeException {
        Ui ui = new Ui();

        if (size < 2) {
            throw new DukeException(ui.showNumError());
        }
    }
}
