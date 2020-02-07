import java.util.ArrayList;
import java.util.List;

/**
 * TaskList reads and do all the instructions, and returns them as Task
 */

public class TaskList {

    String description;
    List<String> allInstructions = new ArrayList<>();
    static List<Task> store = new ArrayList<>();
    Ui ui = new Ui();
    int counter = 0;

    public TaskList() {
        this.allInstructions = allInstructions;
        this.store = store;
    }

    public TaskList(String description) {
        this.description = description;
        this.allInstructions = allInstructions;
        this.store = store;
    }

    public String convertToString() {
        String s = "";
        for (int j = 0; j < allInstructions.size(); j++) {
            s += allInstructions.get(j) + System.lineSeparator();
        }
        return s;
    }

    public List<Task> getDoneTasks() {
        return this.store;
    }

    public List<String> getAllInstructions() {
        return this.allInstructions;
    }

    public void setAllInstructions(List<String> i) {
        this.allInstructions = i;
    }

    public String delete() {
        int num = Integer.parseInt(description.split(" ")[1]);
        assert (num>=1 && num<=store.size()) : "This task does not exists";
        String toPrint = ui.remove(store.get(num - 1)) + "\n";
        store.remove(num - 1);
        toPrint += ui.storeSize(store.size());
        return toPrint;
    }

    public String toDo() throws DukeException {
        if (description.split(" ").length > 1) {
            String todo = description.substring(5);
            Task taskDo = new Todo(todo);
            store.add(taskDo);
            String toPrint = ui.gotIt() + "\n";
            toPrint += taskDo.toString() + "\n";
            toPrint += ui.storeSize(store.size());
            return toPrint;
        } else {
            throw new DukeException("))-: OOPS!!! The description of a todo"
                    + " cannot be empty.");
        }
    }

    public String deadLine() {
        String by = description.split("/")[1].substring(3);
        by += "/" +description.split("/")[2] + "/" + description.split("/")[3];
        String d = description.split("/")[0].substring(9);
        Task deadline = new Deadline(d, by);
        store.add(deadline);
        String toPrint = ui.gotIt() + "\n";
        toPrint += deadline.toString() + "\n";
        toPrint += ui.storeSize(store.size());
        return toPrint;
    }

    public String event() {
        String at = description.split("/")[1].substring(3);
        at += "/" +description.split("/")[2] + "/" + description.split("/")[3];
        String d = description.split("/")[0].substring(6);
        Task event = new Events(d, at);
        store.add(event);
        String toPrint = ui.gotIt() + "\n";
        toPrint += event.toString();
        toPrint += "\n" + ui.storeSize(store.size());
        return toPrint;
    }

    public String done() {
        int num = Integer.parseInt(description.split(" ")[1]);
        assert (num >=1 && num<= store.size()) : "This task does not exists";
        Task t = store.get(num - 1);
        t.markAsDone();
        String toPrint = ui.doneTask() + "\n";
        toPrint += t.toString();
        return toPrint;
    }

    public String find() {
        ui.matchingTask();
        String matching = description.substring(5);
        assert (matching != "") : "user needs to input";
        String toPrint = "";
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getDescription().contains(matching)) {
                toPrint = store.get(i).toString();
            }
        }
        return toPrint;
    }

    public String printList() {
        ui.taskList();
        String toPrint = "";
        for (int i = 0; i < store.size(); i++) {
            counter++;
            toPrint += "\n" + counter + ". " + store.get(i).toString();
            assert store.get(i) != null : "No task available";
        }
        return toPrint;
    }

}
