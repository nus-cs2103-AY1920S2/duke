import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TaskList reads and do all the instructions, and returns them as Task
 */

public class TaskList {

    private String description;
    private List<String> allInstructions = new ArrayList<>();
    static List<Task> store = new ArrayList<>();
    private Ui ui = new Ui();
    private int counter = 0;

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
//        return allInstructions.stream().map(i -> i)
//                .collect(Collectors.joining("\n"));
        for (int i=0; i<allInstructions.size(); i++) {
            s += allInstructions.get(i);
        }
        return s;
    }

//    public List<Task> convertToTask() {
//        for (int i=0; i<allInstructions.size(); i++) {
//            String instruction = allInstructions.get(i);
//            String[] arr = instruction.split(" | ");
//            if (arr[0].equals("E")) {
//
//            }
//
//        }
//    }

    public List<Task> getDoneTasks() {
        return this.store;
    }

    public void setTask(List<Task> i) {
        this.store = i;
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
        Task event = new Event(d, at);
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
        String toPrint = ui.matchingTask() + "\n";
        String matching = description.substring(5);
        assert (matching != "") : "user needs to input";
        toPrint  += store.stream()
                .filter( s -> s.getDescription().contains(matching))
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return toPrint;
    }

    public String date() {
        String date = description.substring(5);
        String toPrint = ui.date(date) + "\n";
        for(int i=0; i<store.size(); i++) {
            Task t= store.get(i);
            if (t instanceof Event) {
                Event e = (Event) t;
                if(e.getDate().equals(date)) {
                    toPrint += e.toString() + "\n";
                }
            }
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if(d.getDate().equals(date)) {
                    toPrint += d.toString() + "\n";
                }
            }
        }
        return toPrint;
    }

    public String view() {
        List<Todo> td = new ArrayList<Todo>();
        List<Deadline> dl = new ArrayList<Deadline>();
        List<Event> evt = new ArrayList<Event>();

        String taskToView = description.substring(5);
        String toPrint = ui.view(taskToView) + "\n";

        if (taskToView.equals("todo")) {
            toPrint += store.stream()
                            .filter( s -> { return s instanceof Todo;})
                            .map(Task::toString)
                            .collect(Collectors.joining("\n"));
        }
        if (taskToView.equals("deadline")) {
            toPrint += store.stream()
                    .filter( s -> {return s instanceof Deadline;})
                    .map(Task::toString)
                    .collect(Collectors.joining("\n"));
        }
        if (taskToView.equals("event")) {
            toPrint += store.stream()
                    .filter( s -> {return s instanceof Event;})
                    .map(Task::toString)
                    .collect(Collectors.joining("\n"));
        }
        return toPrint;

    }


    public String printList() {
        String toPrint = ui.taskList();
        for (int i = 0; i < store.size(); i++) {
            counter++;
            toPrint += "\n" + counter + ". " + store.get(i).toString();
            assert store.get(i) != null : "No task available";
        }
        return toPrint;
    }

}
