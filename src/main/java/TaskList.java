import java.util.ArrayList;

/**
 * Represents task list in Duke.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    // load from storage
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // create new empty list
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns task list as an ArrayList
     *
     * @return Task list as an ArrayList of Tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Creates a new Event based on user input and adds it to the task list.
     *
     * @param input Valid user command in the form "event event_description /at DD/MM/YYYY HHMM"
     * @return Event that was created
     * @throws GooseEmptyDescriptionException If no description provided
     * @throws GooseIllegalFormatException If format is not valid
     */
    public Event createEvent(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] eventArr = input.split(" /at ");
        String[] descriptionSplit = eventArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of an event cannot be empty.");
        } else {
            if (eventArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No event date specified.");
            }

            eventArr = eventArr[1].split(" ");
            String date = parseDate(eventArr[0]);
            String time = eventArr[1];
            taskList.add(new Event(description, date, time));
            return (Event) taskList.get(taskList.size() - 1);
        }
    }

    /**
     * Creates a new Deadline based on user input and adds it to the task list.
     *
     * @param input Valid user command in the form "deadline deadline_description /by DD/MM/YYYY HHMM"
     * @return Deadline that was created
     * @throws GooseEmptyDescriptionException If no description provided
     * @throws GooseIllegalFormatException If format is not valid
     */
    public Deadline createDeadline(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] deadlineArr = input.split(" /by ");
        String[] descriptionSplit = deadlineArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a deadline cannot be empty.");
        } else {
            if (deadlineArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No deadline specified.");
            }

            deadlineArr = deadlineArr[1].split(" ");
            String date = parseDate(deadlineArr[0]);
            String time = deadlineArr[1];
            taskList.add(new Deadline(description, date, time));
            return (Deadline) taskList.get(taskList.size() - 1);
        }
    }

    /**
     * Creates a new Todo based on user input and adds it to the task list.
     *
     * @param inputArr String array of split user input string
     * @return Todo that was created
     * @throws GooseEmptyDescriptionException If no description provided
     */
    public Todo createTodo(String[] inputArr) throws GooseEmptyDescriptionException {
        String description = "";
        for (int i = 1; i < inputArr.length; i++) {
            if (i == inputArr.length - 1) {
                description += inputArr[i];
            } else {
                description += inputArr[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of a todo cannot be empty.");
        } else {
            taskList.add(new Todo(description));
            return (Todo) taskList.get(taskList.size() - 1);
        }
    }

    /**
     * Deletes task from task list array based on the index provided
     *
     * @param index Index of task to be deleted from task list
     * @return Updated task list as an ArrayList
     * @throws GooseTaskExistenceException If index is out of bounds
     */
    public ArrayList<Task> deleteTask(int index) throws GooseTaskExistenceException {
        if (index >= taskList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? Task " + taskList.size() + " doesn't exist. Honk...");
        }

        taskList.remove(index);
        return this.taskList;
    }

    /**
     * Marks task as done from task list array based on the index provided
     *
     * @param index Index of task to be marked as done from task list
     * @return Updated task list as an ArrayList
     * @throws GooseTaskExistenceException If index is out of bounds
     */
    public ArrayList<Task> markDone(int index) throws GooseTaskExistenceException {
        if (index >= taskList.size() || index < 0) {
            throw new GooseTaskExistenceException("You trick Goose? This task doesn't exist. Honk...");
        }

        Task selected = taskList.get(index);
        selected.markAsDone();
        return this.taskList;
    }

    /**
     * Finds and returns an ArrayList of tasks from the task list which contain the search keyword.
     * @param search Search keyword
     * @return ArrayList of Tasks from the task list which contain the search keyword
     */
    public ArrayList<Task> findTask(String search) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task selected = taskList.get(i);
            if (selected.description.contains(search)) {
                foundTasks.add(selected);
            }
        }

        return foundTasks;
    }

    private static String parseDate(String date) {
        String[] dateArr = date.split("/");
        String day = dateArr[0];
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        String month = dateArr[1];
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String year = dateArr[2];

        return year + "-" + month + "-" + day;
    }
}