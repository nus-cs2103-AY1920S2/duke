package dukeApp.parse;

import dukeApp.files.DukeException;
import dukeApp.files.TaskList;

public class Parse {
    String statement;

    public Parse(String statement) {
        this.statement = statement;
    }

    /**
     * Breaks down statement from user to determine task specified
     * @param tasks list of existing tasks
     * @return message in response to user input
     */
    public String decode(TaskList tasks) {
        String[] taskArray = statement.split(" "); //task array is each line from input file
        String msg =  "";
        try {
            if (statement.equals("list")) {
                return listDecode(tasks);
            }
            else if (statement.equals("reminders")) {
                return tasks.reminderList();
            }
            //any command that's not list and reminder
            else if (taskArray.length == 1) {
                throw emptyCommandDecode(taskArray[0]);
            }
            else {
                switch (taskArray[0]) {
                    case "find":
                        msg = tasks.find(taskArray[1]);
                        break;
                    case "delete":
                    case "done":
                        int rank = Integer.parseInt(taskArray[1]); //rank of task to be deleted or marked as done
                        msg = tasks.deleteDone(rank, taskArray[0]);
                        break;
                    case "todo": case "event": case "deadline":
                        statement = statement.substring(statement.indexOf(" "));
                        msg = tasks.add(taskArray[0], statement);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            }
        } catch (DukeException e) {
            msg = e.toString();
        }
        return msg;
    }

    /**
     * Error thrown when no details follow a command that require details, or an unknown command given
     * @param s command given by user
     * @return DukeException that contains the error message
     */
    private DukeException emptyCommandDecode(String s) throws DukeException {
        if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of a " + s + " cannot be empty.\n");
        } else if (s.equals("delete") || s.equals("done")) {
            throw new DukeException("☹ OOPS!!! The task index is missing.\n");
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Actions that follow when a list command is specified
     * @param tasks list of existing tasks
     * @return task list or if an error if it's empty
     */
    private String listDecode(TaskList tasks) {
        String msg = "";
        //print list if there are tasks in the list
        try {
            //if empty throw error
            if (tasks.getSize() == 0) {
                throw new DukeException("There is no task in the list.\n");
            }
            msg = tasks.printList();
        } catch (DukeException e){
            msg = e.toString();
        }
        return msg;
    }
}
