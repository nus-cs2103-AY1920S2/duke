import java.util.ArrayList;
import java.util.List;

public class DukeWorker {

    private List<String> task = new ArrayList<>();

    public String handleRequest(String request) {
        if (request.toLowerCase().equals("bye")) {
            return "Bye ciao adios";
        } else if (request.toLowerCase().equals("list")) {
            if (task.size() == 0) {
                return "No scheduled task yet";
            }
            String list = "1. " + task.get(0);
            for (int i = 2; i < task.size() + 1; i++) {
                list = list + "\n" + i + ". " + task.get(i-1);
            }
            return list;
        } else {
            addToList(request);
            return "Added " + request + " to list";
        }
    }

    private void addToList(String item) {
        task.add(item);
    }

    private List<String> getList() {
        return task;
    }
}
