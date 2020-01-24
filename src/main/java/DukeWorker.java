import java.util.ArrayList;
import java.util.List;

public class DukeWorker {

    private List<Task> task = new ArrayList<>();

    public String handleRequest(String request) {
        String[] token = request.split(" ");
        if (token[0].equals("bye")) {
            return "Bye ciao adios";
        } else if (token[0].equals("list")) {
            if (task.size() == 0) {
                return "No scheduled task yet";
            }
            String list = "1." + task.get(0).getDoneString() + " " + task.get(0).getTaskName();
            for (int i = 2; i < task.size() + 1; i++) {
                list = list + "\n" + i + "." + task.get(i-1).getDoneString() + " " + task.get(i-1).getTaskName();
            }
            return list;
        } else if (token[0].equals("done")) {
            if (token.length < 2 || isNumeric(token[1]) != true) {
                return "Please specify which task to mark as done";
            }
            int taskId = Integer.parseInt(token[1]) - 1;
            if (taskId >= task.size() || taskId < 0) {
                return "No such task number";
            }
            task.get(taskId).markAsDone();
            String response = "Nice! Task(s) marked as done:\n " + task.get(taskId).getDoneString() + " " +
                    task.get(taskId).getTaskName();
            for (int i = 2; i < token.length; i++) {
                if (isNumeric(token[1]) != true) {
                    break;
                } else {
                    if (taskId >= task.size() || taskId < 0) {
                        return "No such task number";
                    }
                    taskId = Integer.parseInt(token[i]) - 1;
                    task.get(taskId).markAsDone();
                    response = response + "\n " + task.get(taskId).getDoneString() + " " +
                            task.get(taskId).getTaskName();
                }
            }
            return response;
        } else {
            addToList(request);
            return "Added " + request + " to list";
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void addToList(String item) {
        Task newTask = new Todos(item);
        task.add(newTask);
    }

    private List<Task> getList() {
        return task;
    }
}
