import java.util.ArrayList;
import java.util.List;

// TaskList reads and do all the instructions

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
        String s ="";
        for (int j =0; j<allInstructions.size(); j++) {
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

    public void delete() {
        int num = Integer.parseInt(description.split(" ")[1]);
        ui.remove(store.get(num-1));
        store.remove(num-1);
        ui.storeSize(store.size());
    }

    public void toDo() throws DukeException {
        if (description.split(" ").length > 1 ) {
            String todo = description.substring(5);
            Task taskDo = new Todo(todo);
            store.add(taskDo);
            ui.gotIt();
            System.out.println(taskDo);
            ui.storeSize(store.size());
        } else {
            throw new DukeException( "))-: OOPS!!! The description of a todo cannot be empty. " );
        }
    }

    public void deadLine() {
        String by = description.split("/")[1].substring(3);
        by += "/" +description.split("/")[2] + "/" + description.split("/")[3];
        String d = description.split("/")[0].substring(9);
        Task deadline = new Deadline(d, by);
        store.add(deadline);
        ui.gotIt();
        System.out.println(deadline);
        ui.storeSize(store.size());
    }

    public void event() {
        String at = description.split("/")[1].substring(3);
        at += "/" +description.split("/")[2] + "/" + description.split("/")[3];
        String d = description.split("/")[0].substring(6);
        Task event = new Events(d, at);
        store.add(event);
        ui.gotIt();
        System.out.println(event);
        ui.storeSize(store.size());
    }

    public void done() {
        int num = Integer.parseInt(description.split(" ")[1]);
        Task t = store.get(num-1);
        t.markAsDone();
        ui.doneTask();
        System.out.println(t);
    }

    public void printList() {
        ui.taskList();
        for (int i =0 ;i< store.size(); i++) {
            counter ++;
            System.out.println(counter + ". " + store.get(i));
        }
    }

}
