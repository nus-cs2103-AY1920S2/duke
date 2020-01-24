import java.util.ArrayList;
import java.util.List;

public class DukeWorker {

    private List<Task> task = new ArrayList<>();

    public String handleRequest(String request) {
        String[] token = request.split(" ", 2);
        if (token[0].equals("bye")) {
            return "Bye ciao adios";
        } else if (token[0].equals("list")) {
            if (task.size() == 0) {
                return "No scheduled task yet";
            }
            String list = "1." + task.get(0);
            for (int i = 2; i < task.size() + 1; i++) {
                list = list + "\n" + i + "." + task.get(i-1);
            }
            return list;
        } else if (token[0].equals("done")) {
            String[] indices = token[1].split(" ");
            if (indices.length < 1 || isNumeric(indices[0]) != true) {
                return "Please specify which task to mark as done";
            }
            int taskId = Integer.parseInt(indices[0]) - 1;
            if (taskId >= task.size() || taskId < 0) {
                return "No such task number";
            }
            task.get(taskId).markAsDone();
            String response = "Nice! Task(s) marked as done:\n " + task.get(taskId).getDoneString() + " " +
                    task.get(taskId).getTaskName();

            for (int i = 1; i < indices.length; i++) {
                if (isNumeric(indices[i]) != true) {
                    break;
                } else {
                    taskId = Integer.parseInt(indices[i]) - 1;
                    if (taskId >= task.size() || taskId < 0) {
                        break;
                    }
                    task.get(taskId).markAsDone();
                    response = response + "\n " + task.get(taskId).getDoneString() + " " +
                            task.get(taskId).getTaskName();
                }
            }
            return response;
        } else if (token[0].equals("todo") || token[0].equals("deadline") || token[0].equals("event")) {
           if (addToList(token[1], token[0])) {
               return "I've added this task to the list:\n " + task.get(task.size() - 1) + "\n" +
                       "Now you have " + task.size() + " task(s) in the list";
           } else {
               return "Adding task failed, please check your request";
           }
        } else {
            return "I don't understand what you want";
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //
    private boolean addToList(String item, String type) {
        Task newTask;
        if (type.equals("todo")) {
            newTask = new Todos(item);
        } else if (type.equals("deadline")) {
            String[] tokens = item.split("/", 2);
            if (tokens.length < 2) {
                return false;
            }
            if (!tokens[1].substring(0, 2).equals("by")) {
                return false;
            }
            newTask = new Deadlines(tokens[0].trim(), tokens[1].substring(2).trim());
        } else {
            String[] tokens = item.split("/", 2);
            if (tokens.length < 2) {
                return false;
            }
            if (!tokens[1].substring(0, 2).equals("at")) {
                return false;
            }
            newTask = new Events(tokens[0].trim(), tokens[1].substring(2).trim());
        }
        task.add(newTask);
        return true;
    }

    private List<Task> getList() {
        return task;
    }
}
