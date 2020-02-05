package dukeApp.parse;

import dukeApp.files.DukeException;
import dukeApp.files.TaskList;

public class Parse {
    String statement;

    public Parse(String statement) {
        this.statement = statement;
    }

    public void decode(TaskList tasks) throws DukeException{
        String[] taskArray = new String[2];

        try {
            if (statement.equals("list") && tasks.getSize() != 0) {
                tasks.printList();
            }
            //if empty throw error
            else if (statement.equals("list")) {
                throw new DukeException("There is no task in the list.\n");
            }

            //any command that's not list
            else {
                //task array is each line from input file
                taskArray = statement.split(" ");
                if (taskArray.length == 1) {
                    if (taskArray[0].equals("todo") || taskArray[0].equals("deadline") || taskArray[0].equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a " +taskArray[0]+ " cannot be empty.\n");
                    }
                    else if (taskArray[0].equals("delete") || taskArray[0].equals("done")) {
                        throw new DukeException("☹ OOPS!!! The task index is missing.\n");
                    }
                    else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    }
                } else if (taskArray[0].equals("find")) {
                    tasks.find(taskArray[1]);
                } else if (taskArray[0].equals("delete") || taskArray[0].equals("done")) {
                    int rank = Integer.parseInt(taskArray[1]); //rank of task to be deleted or marked as done
                    tasks.deleteDone(rank, taskArray[0]);
                } else {
                    tasks.add(taskArray[0], statement);
                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
