package dukeApp.parse;

import dukeApp.files.DukeException;
import dukeApp.files.TaskList;

public class Parse {
    String statement;

    public Parse(String statement) {
        this.statement = statement;
    }

    public void decode(TaskList tasks) throws DukeException {
        String[] taskArray = statement.split(" "); //task array is each line from input file

        try {
            if (statement.equals("list")) {
                listDecode(tasks);
            }
            else if (statement.equals("reminders")) {
                tasks.reminderList();
            }
            //any command that's not list and reminder
            else if (taskArray.length == 1) {
                emptyCommandDecode(taskArray[0]);
            } else {
                switch (taskArray[0]) {
                    case "find":
                        tasks.find(taskArray[1]);
                        break;
                    case "delete":
                    case "done":
                        int rank = Integer.parseInt(taskArray[1]); //rank of task to be deleted or marked as done
                        tasks.deleteDone(rank, taskArray[0]);
                        break;
                    default:
                        statement = statement.substring(statement.indexOf(" "));
                        tasks.add(taskArray[0], statement);
                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private void emptyCommandDecode(String s) throws DukeException {
        if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of a " + s + " cannot be empty.\n");
        } else if (s.equals("delete") || s.equals("done")) {
            throw new DukeException("☹ OOPS!!! The task index is missing.\n");
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    private void listDecode(TaskList tasks) {
        //print list if there are tasks in the list
        try {
            //if empty throw error
            if (tasks.getSize() == 0) {
                throw new DukeException("There is no task in the list.\n");
            }
            tasks.printList();
        } catch (DukeException e){
        System.out.println(e);
        }
    }
}
