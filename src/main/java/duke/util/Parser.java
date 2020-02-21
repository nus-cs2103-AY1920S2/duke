package duke.util;

import java.util.Scanner;
import duke.task.Task;

public class Parser {

    /**
     * Checks if input String is a marking task request.
     * @param str
     * @return true if str is "done %d" and false otherwise.
     */
    public int isMarkingTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);
        assert sc.hasNext();

        while (sc.hasNext()) {
            ss = sc.next();
            if (!ss.equals("done"))
                break;
            if (!sc.hasNextInt())
                break;
            ret = sc.nextInt() - 1;
            if (sc.hasNext())
                ret = -2;
            break;
        }

        sc.close();
        return ret;
    }

    /**
     * Checks if input String is a deleting task request.
     * @param str
     * @return true if str is "delete %d" and false otherwise.
     */
    public int isDeleteTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);
        assert sc.hasNext();

        while (sc.hasNext()) {
            ss = sc.next();
            if (!ss.equals("delete"))
                break;
            if (!sc.hasNextInt())
                break;
            ret = sc.nextInt() - 1;
            if (sc.hasNext())
                ret = -2;
            break;
        }

        sc.close();
        return ret;
    }

    /**
     * Determines the type of the command.
     * @param str
     * @return TaskType toDo, deadline, event, or unknown.
     */
    public Task.TaskType commandType(String str) {
        Task.TaskType ret = Task.TaskType.UNKNOWN;
        Scanner sc = new Scanner(str);

        while (sc.hasNext()) {
            String ss = sc.next();
            if (ss.equals(Task.toDoCommand))
                ret = Task.TaskType.TODO;
            else if (ss.equals(Task.deadlineCommand))
                ret = Task.TaskType.DEADLINE;
            else if (ss.equals(Task.eventCommand))
                ret = Task.TaskType.EVENT;
            break;
        }

        sc.close();
        return ret;
    }

    /**
     * Check if command is a find command.
     * @param str
     * @return true if command is find, false otherwise.
     */
    public boolean isFindRequest(String str) {
        Scanner sc = new Scanner(str);
        boolean ret = sc.hasNext() && sc.next().equals("find");
        sc.close();
        return ret;
    }
}
