package duke.task;

import duke.exception.DukeException;
import duke.storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The Class TaskList is class to keep track of all the task that is created.
 */
public class TaskList {

    /**
     * The taskList.
     */
    private ArrayList<Task> taskList;


    /**
     * Instantiates a new task list.
     *
     * @param taskList the taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Instantiates a new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Gets the taskList.
     *
     * @return the taskList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * this method is to replace the taskList.
     *
     * @param newList replace the taskList
     */
    public void setTaskList(ArrayList<Task> newList) {
        this.taskList = newList;
    }

    /**
     * Adds the todo.
     *
     * @param input the input
     * @param data  the data
     * @return String to denote addTodo
     * @throws DukeException the duke exception
     */
    public String addTodo(String input, Storage data) throws DukeException {
        StringBuilder output = new StringBuilder();
        String remainString = input.replaceFirst("todo", "").trim();
        assert remainString.length() <= 0 : "Invalid addTodo input";
        if (remainString.length() >= 1) {
            ToDos newToDo = new ToDos(remainString);
            output.append(add(newToDo));
            data.appendTodoToFile("T", 0, remainString);
        } else {
            return new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n").toString();
        }
        return output.toString();
    }

    /**
     * Adds the deadline.
     *
     * @param input the input
     * @param data  the data
     * @return String to denote addDeadline
     * @throws DukeException the duke exception
     */
    public String addDeadline(String input, Storage data) throws DukeException {
        StringBuilder output = new StringBuilder();
        String remainString = input.replaceFirst("deadline", "").trim();
        assert remainString.length() <= 0 : "Invalid addDeadline input";
        if (remainString.length() >= 1) {
            try {
                String[] detail = remainString.split(" /by ");
                String[] dateTime = detail[1].trim().split(" ");
                LocalDate deadLineDate = LocalDate.parse(dateTime[0].trim());
                LocalTime deadLineTime = LocalTime.parse(dateTime[1].trim());
                Deadlines newDeadLine = new Deadlines(detail[0], deadLineDate, deadLineTime);
                output.append(add(newDeadLine));
                data.appendDeadlineToFile("D", 0, detail[0], dateTime[0], dateTime[1]);
            } catch (Exception e) {
                return new DukeException("OOPS!!! Please enter in the format of : "
                        + " task description, YYYY-MM-DD and HH:MM \n").toString();
            }
        } else {
            throw new DukeException("OOPS!!! The description of a deadline is wrong");
        }
        return output.toString();
    }

    /**
     * Adds the event.
     *
     * @param input the input
     * @param data  the data
     * @return String to denote addEvent
     * @throws DukeException the duke exception
     */
    public String addEvent(String input, Storage data) throws DukeException {
        StringBuilder output = new StringBuilder();
        String remainString = input.replaceFirst("event", "").trim();
        assert remainString.length() <= 0 : "Invalid addEvent input";
        if (remainString.length() >= 1) {
            try {
                String[] detail = remainString.split(" /at ");
                for (int l = 0; l < detail.length; l++) {
                    System.out.println(detail[l]);
                }
                String[] splitDateTime = detail[1].trim().split(" ");
                LocalDate eventDate = LocalDate.parse(splitDateTime[0].trim());
                LocalTime eventStartTime = LocalTime.parse(splitDateTime[1].trim());
                LocalTime eventEndTime = LocalTime.parse(splitDateTime[2].trim());
                Events newEvent = new Events(detail[0], eventDate, eventStartTime, eventEndTime);
                output.append(add(newEvent));
                data.appendEventToFile("E", 0, detail[0], splitDateTime[0], splitDateTime[1], splitDateTime[2]);
            } catch (Exception e) {
                return new DukeException("OOPS!!! Please enter in the format of : "
                        + "description, YYYY-MM-DD HH:MM (starting time) HH:MM (ending time)\n").toString();
            }
        } else {
            throw new DukeException("OOPS!!! The description of an event is wrong.\n");
        }
        return output.toString();

    }

    /**
     * List.
     *
     * @return a string which describe the all the task
     */
    public String list() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        int taskSize = this.taskList.size();
        for (int i = 0; i < taskSize; i++) {
            int count = i + 1;
            output.append(count + ". " + this.taskList.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Adds the add into taskList.
     *
     * @param newTask the new task
     * @return String to denote the task is added and amount of task in the list
     */
    public String add(Task newTask) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task: \n");
        this.taskList.add(newTask);
        output.append(newTask.toString());
        int amtOfTask = this.taskList.size();
        if (amtOfTask <= 1) {
            output.append("Now you have " + amtOfTask + " task in list\n");
        } else {
            output.append("Now you have " + amtOfTask + " tasks in list\n");
        }
        return output.toString();
    }

    /**
     * Done.
     *
     * @param number the number
     * @param data   the data
     * @return String to denote that the task is mark as done
     * @throws DukeException the duke exception
     */
    public String done(int number, Storage data) throws DukeException {
        StringBuilder output = new StringBuilder();
        if (number <= this.taskList.size() && number >= 1) {
            Task getTask = this.taskList.get(number - 1);
            if (getTask.isDone) {
                output.append("Error, task is already done\n");
            } else {
                getTask.markAsDone();
                output.append("Nice!. I've marked this task as done:\n");
                output.append(getTask.toString());
                data.updateFile(this.taskList);
            }
        } else {
            throw new DukeException("OOPS!!! There is no such tasks\n");
        }
        return output.toString();
    }

    /**
     * Delete.
     *
     * @param number the number
     * @param data   the data
     * @return String to state the task is deleted and how many left
     * @throws DukeException the duke exception
     */
    public String delete(int number, Storage data) throws DukeException {
        StringBuilder output = new StringBuilder();
        if (number <= this.taskList.size() && number >= 1) {
            Task getTask = this.taskList.get(number - 1);
            this.taskList.remove(number - 1);
            output.append("Noted. I've removed this task:\n");
            output.append(getTask.toString());
            int amtOfTask = this.taskList.size();
            if (amtOfTask <= 1) {
                output.append("Now you have " + amtOfTask + " task in list\n");
            } else {
                output.append("Now you have " + amtOfTask + " tasks in list\n");
            }
        } else {
            return new DukeException("OOPS!!! There is no such tasks\n").toString();
        }
        data.updateFile(this.taskList);
        return output.toString();
    }


}
