import java.util.Scanner;

public class Parser {
    public int isMarkingTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);

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

    public int isDeleteTaskRequest(String str) {
        int ret = -2;
        String ss = "";
        Scanner sc = new Scanner(str);

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

    public Task.TaskType commandType(String str) {
        Task.TaskType ret = Task.TaskType.unknown;
        Scanner sc = new Scanner(str);

        while (sc.hasNext()) {
            String ss = sc.next();
            if (ss.equals(Task.toDoCommand))
                ret = Task.TaskType.toDo;
            else if (ss.equals(Task.deadlineCommand))
                ret = Task.TaskType.deadline;
            else if (ss.equals(Task.eventCommand))
                ret = Task.TaskType.event;
            break;
        }

        sc.close();
        return ret;
    }
}
