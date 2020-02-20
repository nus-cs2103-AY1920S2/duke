import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Parser object deals with making sense of the user commands and can find, add or delete tasks from the
 * <code>task list</code> it stores.
 */
public class Parser {
    protected TaskList tasks;
    protected ArrayList<Task> taskList;
    protected Ui ui;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.taskList = tasks.getList();
        this.ui = new Ui();
    }

    /**
     * Parses a command to mark a certain task in the task list as done.
     *
     * @param command command inputted by user to mark a certain task as done.
     * @return message showing the task completed by user.
     * @throws DukeException if command for task that is completed is not specified.
     */
    public String handleDone(String command) throws DukeException {
        String[] strArr = command.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("UNK_TASK_DONE");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 | index > taskList.size()) {
            throw new DukeException("OUT_OF_BOUNDS");
        }
        Task currTask = taskList.get(index);
        currTask.setDone();
        return ui.printTaskMarkedDone(currTask);
    }

    /**
     * Parses a command to find a certain task by searching for a keyword.
     *
     * @param command keyword inputted by user to look for a certain task.
     * @return message showing tasks that contain the keyword the user has inputted.
     * @throws DukeException if keyword to look for is not specified.
     */
    public String handleFind(String command) throws DukeException {
        String[] strArr = command.split(" ", 2);
        if (strArr.length == 1) {
            throw new DukeException("UNK_TASK_TO_FIND");
        }
        return ui.printTasksFound(taskList, strArr[1]);
    }

    /**
     * Parses a 'Todo' event.
     *
     * @param command command inputted by user to add a Todo task.
     * @return message showing the Todo task user has inputted and the total number of tasks in the task list.
     * @throws DukeException if command for Todo task is not inputted.
     */
    public String handleTodo(String command) throws DukeException {
        String[] strArr = command.split(" ", 2);
        if (strArr.length == 1) {
            throw new DukeException("TODO_NO_DESC");
        }
        String todoCommand = strArr[1];
        Todo newTask = new Todo(todoCommand);
        tasks.addTask(newTask);
        return ui.printTodoTask(newTask, taskList);
    }

    /**
     * Parses a 'Deadline' event.
     *
     * @param command command inputted by user to add a Deadline task.
     * @return message showing the Deadline task that the user has just inputted and the total number of tasks in the
     *     task list.
     * @throws DukeException if command or the deadline date and time for the task is not inputted.
     */
    public String handleDeadline(String command) throws DukeException {
        String[] strArr = command.split(" ", 2);
        if (strArr.length == 1) {
            throw new DukeException("DEADLINE_NO_DESC");
        }
        String[] cmdArr = strArr[1].split("/", 2);
        if (cmdArr.length == 1) {
            throw new DukeException("DEADLINE_NO_DEADLINE");
        }
        String deadlineCommand = cmdArr[0];
        String deadlineDetails = cmdArr[1].split(" ", 2)[1];
        String[] deadlineDetailsArr = deadlineDetails.split(" ", 2);
        String deadlineDate = deadlineDetailsArr[0];
        String deadlineTime = deadlineDetailsArr[1];
        Deadline newTask = new Deadline(deadlineCommand, deadlineDate, deadlineTime);
        tasks.addTask(newTask);
        return ui.printDeadlineTask(newTask, taskList);
    }

    /**
     * Parses a 'Event' event.
     *
     * @param command command inputted by user to add a Event task.
     * @return message showing the Event task that the user has just inputted and the total number of tasks in the
     *     task list.
     * @throws DukeException if command or the event date and time for the task is not inputted.
     */
    public String handleEvent(String command) throws DukeException {
        String[] strArr = command.split(" ", 2);
        if (strArr.length == 1) {
            throw new DukeException("EVENT_NO_DESC");
        }
        String[] cmdArr = strArr[1].split("/", 2);
        if (cmdArr.length == 1) {
            throw new DukeException("EVENT_NO_DATE_AND_TIME");
        }
        String eventCommand = cmdArr[0];
        String timing = cmdArr[1].split(" ", 2)[1];
        Event newTask = new Event(eventCommand, timing);
        tasks.addTask(newTask);
        return ui.printEventTask(newTask, taskList);
    }

    /**
     * Parses a command to delete a certain task.
     *
     * @param command String with command of the task to be deleted.
     * @return message showing the remaining tasks in task list after task mentioned is deleted from the list.
     * @throws DukeException if the index of the task to be deleted is not inputted.
     */
    public String handleDelete(String command) throws DukeException {
        String[] strArr = command.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("UNK_TASK_TO_DELETE");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 | index > taskList.size()) {
            throw new DukeException("OUT_OF_BOUNDS");
        }
        Task currTask = taskList.get(index);
        tasks.deleteTask(currTask);
        return ui.printRemainingList(currTask, taskList);
    }

    /**
     * Parses a command to update a particular task.
     *
     * @param command String with command of the task previously inputted and the details to be updated for that task.
     * @return message that updating of the details of a task has been done.
     * @throws DukeException if the command cannot be found in any of the tasks previously inputted.
     */
    public String handleUpdate(String command) throws DukeException {
        Task currTask = null;
        String[] strArr = command.split(" ", 2)[1].split("/", 2);
        String commandToFind = strArr[0];
        String detailsToUpdate = strArr[1];
        for (Task task : taskList) {
            if (task.getCommand().trim().equals(commandToFind.trim())) {
                currTask = task;
            }
        }
        if (currTask == null) {
            throw new DukeException("COMMAND_NOT_FOUND");
        }
        currTask.updateDetails(detailsToUpdate);
        return ui.printUpdatingDone();
    }

    /**
     * Makes sense of the user commands.
     *
     * @param command user commands inputted by user.
     * @return String for the command to be outputted by DukeBot.
     */
    public String parse(String command) {
        try {
            if (command.equals("manual")) {
                return ui.printManual();
            } else if (command.contains("done")) {
                return handleDone(command);
            } else if (command.contains("todo")) {
                return handleTodo(command);
            } else if (command.contains("deadline")) {
                return handleDeadline(command);
            } else if (command.contains("event")) {
                return handleEvent(command);
            } else if (command.equals("list")) {
                return ui.printList(taskList);
            } else if (command.contains("delete")) {
                return handleDelete(command);
            } else if (command.contains("find")) {
                return handleFind(command);
            } else if (command.contains("update")) {
                return handleUpdate(command);
            } else {
                throw new DukeException("OTHERS");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DateTimeParseException exception) {
            return new DukeException("OTHERS").toString();
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

    /**
     * Updates the task list with tasks noted down in the file saved in hard disk.
     *
     * @param line String containing the type of event (T / D / E) and the corresponding command and details.
     * @return task list which is updated with tasks noted down in the file saved in hard disk.
     */
    public ArrayList<Task> loadText(String line) {
        String[] strArr = line.split(" - ");
        switch (strArr[0]) {
        case "T":
            Todo newTodoTask = new Todo(strArr[2]);
            tasks.addTask(newTodoTask);
            if (strArr[1].equals("1")) {
                newTodoTask.setDone();
            }
            break;
        case "D":
            String[] deadlineArr = strArr[3].split(" ", 2);
            Deadline newDeadlineTask = new Deadline(strArr[2], deadlineArr[0], deadlineArr[1]);
            tasks.addTask(newDeadlineTask);
            if (strArr[1].equals("1")) {
                newDeadlineTask.setDone();
            }
            break;
        case "E":
            Event newEventTask = new Event(strArr[2], strArr[3]);
            tasks.addTask(newEventTask);
            if (strArr[1].equals("1")) {
                newEventTask.setDone();
            }
            break;
        default:
            break;
        }
        return taskList;
    }
}
