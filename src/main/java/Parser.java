import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Parser object deals with making sense of the user commands and can find, add or delete tasks from the
 * <code>task list</code> it stores.
 */
public class Parser {
    protected ArrayList<Task> taskList;
    protected Ui ui;

    public Parser(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public String handleDone (String command) throws DukeException {
        String[] strArr = command.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("UNK_TASK_DONE");
        }
        Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
        currTask.setDone();
        return ui.printTaskMarkedDone(currTask);
    }

    public String handleTodo (String command) throws DukeException {
        String[] strArr = command.split(" ", 2);

        if (strArr.length == 1) {
            throw new DukeException("TODO_NO_DESC");
        }
        Todo newTask = new Todo(strArr[1]);
        taskList.add(newTask);
        return ui.printTodoTask(newTask, taskList);
    }

    public String handleDeadline (String command) throws DukeException {
        String[] strArr = command.split(" ", 2);

        if (strArr.length == 1) {
            throw new DukeException("DEADLINE_NO_DESC");
        }
        String[] cmdArr = strArr[1].split("/", 2);
        if (cmdArr.length == 1) {
            throw new DukeException("DEADLINE_NO_DEADLINE");
        }
        command = cmdArr[0];
        String deadline = cmdArr[1].split(" ", 2)[1];
        String[] deadlineArr = deadline.split(" ", 2);
        Deadline newTask = new Deadline(command, deadlineArr[0], deadlineArr[1]);
        taskList.add(newTask);
        return ui.printDeadlineTask(newTask, taskList);
    }

    public String handleEvent (String command) throws DukeException {
        String[] strArr = command.split(" ", 2);

        if (strArr.length == 1) {
            throw new DukeException("EVENT_NO_DESC");
        }
        String[] cmdArr = strArr[1].split("/", 2);
        if (cmdArr.length == 1) {
            throw new DukeException("EVENT_NO_DATE_AND_TIME");
        }
        command = cmdArr[0];
        String timing = cmdArr[1].split(" ", 2)[1];
        Event newTask = new Event(command, timing);
        taskList.add(newTask);
        return ui.printEventTask(newTask, taskList);
    }

    public String handleDelete (String command) throws DukeException {
        String[] strArr = command.split(" ");
        if (strArr.length == 1) {
            throw new DukeException("UNK_TASK_TO_DELETE");
        }
        Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
        taskList.remove(currTask);
        return ui.printRemainingList(currTask, taskList);
    }

    /**
     * Makes sense of the user commands.
     *
     * @param command user commands inputted by user through standard output.
     * @return String for the command to be outputted by DukeBot
     */
    public String parse(String command) {
        try {
            if (command.contains("done")) {
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
                String[] strArr = command.split(" ", 2);
                return ui.printTasksFound(taskList, strArr[1]);
            } else {
                throw new DukeException("OTHERS");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DateTimeParseException exception) {
            return new DukeException("OTHERS").toString();
        } catch (DukeException exception) {
            return exception.toString();
        }
    }

    public ArrayList<Task> loadText(String line) {
        String[] strArr = line.split(" - ");
        switch (strArr[0]) {
        case "T":
            Todo newTodoTask = new Todo(strArr[2]);
            taskList.add(newTodoTask);
            if (strArr[1].equals("1")) {
                newTodoTask.setDone();
            }
            break;
        case "D":
            String[] deadlineArr = strArr[3].split(" ", 2);
            Deadline newDeadlineTask = new Deadline(strArr[2], deadlineArr[0], deadlineArr[1]);
            taskList.add(newDeadlineTask);
            if (strArr[1].equals("1")) {
                newDeadlineTask.setDone();
            }
            break;
        case "E":
            Event newEventTask = new Event(strArr[2], strArr[3]);
            taskList.add(newEventTask);
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
