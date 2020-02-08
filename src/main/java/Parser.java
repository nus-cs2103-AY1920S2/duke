import java.util.ArrayList;

/**
 * A Parser object deals with making sense of the user commands and can find, add or delete tasks from the
 * <code>task list</code> it stores.
 */
public class Parser {
    protected ArrayList<Task> taskList;
    protected Ui ui;

    public enum TypeOfError {
        TODO_NO_DESC, DEADLINE_NO_DESC, DEADLINE_NO_DEADLINE, EVENT_NO_DESC, EVENT_NO_DATE_AND_TIME, OTHERS,
        UNK_TASK_DONE, UNK_TASK_TO_DELETE
    }

    public Parser(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public String toParse(String command) {
        if (command.contains("todo")) {
            return "TODO " + command;
        } else {
            return command;
        }
    }

    /**
     * Makes sense of the user commands.
     *
     * @param command user commands inputted by user through standard output.
     * @return String for the command to be outputted by DukeBot
     */
    public String parse(String command) {
        if (command.contains("done")) {
            String[] strArr = command.split(" ");
            if (strArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.UNK_TASK_DONE.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
            currTask.setDone();
            return ui.printTaskMarkedDone(currTask);
        } else if (command.contains("todo")) {
            String[] strArr = command.split(" ", 2);

            if (strArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.TODO_NO_DESC.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            Todo newTask = new Todo(strArr[1]);
            taskList.add(newTask);
            return ui.printTodoTask(newTask, taskList);
        } else if (command.contains("deadline")) {
            String[] strArr = command.split(" ", 2);

            if (strArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.DEADLINE_NO_DESC.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            String[] cmdArr = strArr[1].split("/", 2);
            if (cmdArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.DEADLINE_NO_DEADLINE.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            command = cmdArr[0];
            String deadline = cmdArr[1].split(" ", 2)[1];
            String[] deadlineArr = deadline.split(" ", 2);
            Deadline newTask = new Deadline(command, deadlineArr[0], deadlineArr[1]);
            taskList.add(newTask);
            return ui.printDeadlineTask(newTask, taskList);
        } else if (command.contains("event")) {
            String[] strArr = command.split(" ", 2);

            if (strArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.EVENT_NO_DESC.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            String[] cmdArr = strArr[1].split("/", 2);
            if (cmdArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.EVENT_NO_DATE_AND_TIME.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            command = cmdArr[0];
            String timing = cmdArr[1].split(" ", 2)[1];
            Event newTask = new Event(command, timing);
            taskList.add(newTask);
            return ui.printEventTask(newTask, taskList);
        } else if (command.equals("list")) {
            return ui.printList(taskList);
        } else if (command.contains("delete")) {
            String[] strArr = command.split(" ");
            if (strArr.length == 1) {
                try {
                    throw new DukeException(TypeOfError.UNK_TASK_TO_DELETE.ordinal());
                } catch (DukeException e) {
                    return e.toString();
                }
            }
            Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
            taskList.remove(currTask);
            return ui.printRemainingList(currTask, taskList);
        } else if (command.contains("find")) {
            String[] strArr = command.split(" ", 2);
            return ui.printTasksFound(taskList, strArr[1]);
        } else {
            try {
                throw new DukeException(TypeOfError.OTHERS.ordinal());
            } catch (DukeException e) {
                return e.toString();
            }
        }
    }
}
