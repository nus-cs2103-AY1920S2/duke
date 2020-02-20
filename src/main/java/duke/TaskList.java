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
     * @param tasks that user has
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to add task into task list.
     *
     * @param arr User input
     */
    public String add(String[] arr) {
        try {
            String taskType = arr[0];
            String[] temp;
            String task = "";
            checkDescription(arr.length);
            Task newTask = new Task("");
            String response = "";
            int initialSize = this.tasks.size();

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
                    response = ui.showDateError();
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
                    response = ui.showDateTimeError();
                }
                break;

            default:
                task = "";
            }

            if (!newTask.description.equals("")) {
                response = ui.showTaskAdded(newTask, this.tasks);
            }

            assert (this.tasks.size() - initialSize) == 1 : "Task list should increment by 1";
            return response;

        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Method to delete task in task list.
     *
     * @param arr User input
     */
    public String delete(String[] arr) {
        try {
            checkNum(arr.length);
            checkValid(arr[1]);
            int taskNum = Integer.parseInt(arr[1]) - 1;
            String response = "";
            int initialSize = this.tasks.size();

            if (this.tasks.size() > taskNum) {
                Task current = this.tasks.get(taskNum);
                this.tasks.remove(taskNum);
                response = ui.showTaskDeleted(current, this.tasks);

            } else {
                response = ui.showTaskError();
            }

            assert (initialSize - this.tasks.size()) == 1 : "Task list should decrease by 1";
            return response;

        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Method to mark task as done.
     *
     * @param arr User input
     */
    public String done(String[] arr) {
        try {
            checkNum(arr.length);
            checkValid(arr[1]);
            int taskNum = Integer.parseInt(arr[1]) - 1;
            String response = "";

            if (this.tasks.size() > taskNum) {
                Task current = this.tasks.get(taskNum);
                current.markDone();
                assert current.isDone == true : "Task should be marked as done";
                response = ui.showDone(current);

            } else {
                response = ui.showTaskError();
            }

            return response;

        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Method to find tasks with matching keywords.
     *
     * @param arr User input
     */
    public String find(String[] arr) {
        String input = arr[1];
        ArrayList<Task> temp = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.description.contains(input)) {
                temp.add(task);
            }
        }
        return ui.showFound(temp);
    }

    /**
     * Method to check if user has entered task description.
     *
     * @param size of String array of user input
     * @throws DukeException error
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
     * @param input of user
     * @throws DukeException error
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
     * @param size of String array of user input
     * @throws DukeException error
     */
    public static void checkNum(int size) throws DukeException {
        Ui ui = new Ui();

        if (size < 2) {
            throw new DukeException(ui.showNumError());
        }
    }
}
