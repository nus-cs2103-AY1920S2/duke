import java.util.ArrayList;

/**
 * A Parser object deals with making sense of the user commands and can find, add or delete tasks from the
 * <code>task list</code> it stores.
 */
public class Parser {
    protected ArrayList<Task> taskList;
    protected Ui ui;

    public enum typeOfError {
        TODO_NO_DESC, DEADLINE_NO_DESC, DEADLINE_NO_DEADLINE, EVENT_NO_DESC, EVENT_NO_DATE_AND_TIME, OTHERS,
        UNK_TASK_DONE, UNK_TASK_TO_DELETE
    }

    public Parser(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Makes sense of the user commands.
     *
     * @param command user commands inputted by user through standard output.
     * @throws DukeException if information of a task provided is insufficient or if command is an unknown command.
     */
    public void parse(String command) throws DukeException {
        if (command.contains("done")) {
            String[] strArr = command.split(" ");
                if (strArr.length == 1) {
                    throw new DukeException(typeOfError.UNK_TASK_DONE.ordinal());
                }
                Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
                currTask.setDone();
                ui.printTaskMarkedDone(currTask);
        } else if (command.contains("todo")) {
            String[] strArr = command.split(" ", 2);

                if (strArr.length == 1) {
                    throw new DukeException(typeOfError.TODO_NO_DESC.ordinal());
                }
                Todo newTask = new Todo(strArr[1]);
                taskList.add(newTask);
                ui.printTodoTask(newTask, taskList);
        } else if (command.contains("deadline")) {
            String[] strArr = command.split(" ", 2);

                if (strArr.length == 1) {
                    throw new DukeException(typeOfError.DEADLINE_NO_DESC.ordinal());
                }
                String[] cmdArr = strArr[1].split("/", 2);
                    if (cmdArr.length == 1) {
                        throw new DukeException(typeOfError.DEADLINE_NO_DEADLINE.ordinal());
                    }
                    command = cmdArr[0];
                    String deadline = cmdArr[1].split(" ", 2)[1];
                    String[] deadlineArr = deadline.split(" ", 2);
                    Deadline newTask = new Deadline(command, deadlineArr[0], deadlineArr[1]);
                    taskList.add(newTask);
                    ui.printDeadlineTask(newTask, taskList);
        } else if (command.contains("event")) {
            String[] strArr = command.split(" ", 2);

                if (strArr.length == 1) {
                    throw new DukeException(typeOfError.EVENT_NO_DESC.ordinal());
                }
                String[] cmdArr = strArr[1].split("/", 2);
                    if (cmdArr.length == 1) {
                        throw new DukeException(typeOfError.EVENT_NO_DATE_AND_TIME.ordinal());
                    }
                    command = cmdArr[0];
                    String timing = cmdArr[1].split(" ", 2)[1];
                    Event newTask = new Event(command, timing);
                    taskList.add(newTask);
                    ui.printEventTask(newTask, taskList);
        } else if (command.equals("list")) {
            ui.printList(taskList);
        } else if (command.contains("delete")) {
            String[] strArr = command.split(" ");
            if (strArr.length == 1) {
                throw new DukeException(typeOfError.UNK_TASK_TO_DELETE.ordinal());
            }
            Task currTask = taskList.get(Integer.parseInt(strArr[1]) - 1);
            taskList.remove(currTask);
            ui.printRemainingList(currTask, taskList);
        } else if (command.contains("find")) {
            String[] strArr = command.split(" ", 2);
            ui.printTasksFound(taskList, strArr[1]);
        } else {
            throw new DukeException(typeOfError.OTHERS.ordinal());
        }
    }
}
