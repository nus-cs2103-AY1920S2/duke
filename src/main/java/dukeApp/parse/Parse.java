package dukeApp.parse;
import dukeApp.files.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parse {
    String statement;

    public Parse(String statement) {
        this.statement = statement;
    }

    public boolean decode(TaskList tasks) {
        String taskArray[] = new String[2];

        if (statement.equals("bye")) {
            return true;
        }
        else {
            if (statement.equals("list") && tasks.getSize() != 0) {
                tasks.printList();
            }
            //if empty throw error
            else if (statement.equals("list")) {
                Ui ui = new Ui();
                DukeException error = new DukeException();
                System.out.println(error.empty());
                System.out.println();
            }

            //any command that's not list
            else {
                //task array is each line from input file
                taskArray = statement.split(" ");
                if (taskArray.length == 1) {
                    Ui ui = new Ui();
                    DukeException error = new DukeException();
                    System.out.println(error.errorMsg(taskArray[0]));
                    System.out.println();
                }
                else if  (taskArray[0].equals("find")) {
                    tasks.find(taskArray[1]);
                }
                else if (taskArray[0].equals("delete") || taskArray[0].equals("done")) {
                    int rank = Integer.parseInt(taskArray[1]); //rank of task to be deleted or marked as done
                    tasks.deleteDone(rank, taskArray[0]);
                }
                else {
                    tasks.add(taskArray[0], statement);
                }
            }
            return false;
        }
    }
}
