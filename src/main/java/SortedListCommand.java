import java.util.ArrayList;
import java.util.HashMap;

public class SortedListCommand extends Command {

    public SortedListCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage) throws DukeException {
        HashMap<Integer, ArrayList<Task>> map = new HashMap<>();
        ArrayList<Task> tasks = list.getTaskList();
        this.initializeHashMap(map);
        for (Task task : tasks) {
            map.get(task.getPriority()).add(task); //add task to respective priority
        }
        String listings = "";
        int currentPriority = 1;
        for (ArrayList<Task> listing : map.values()) {
            int counter = 1;
            listings += ui.getDecoration().trim() + "\n";
            listings += this.getPriorityString(currentPriority) + "\n";
            listings += ui.getDecoration().trim() + "\n";
            for (Task task: listing) {
                listings += counter + "." + task.toString();
                if (counter != list.getSize()) {
                    listings += "\n";
                }
                counter++;
            }
            currentPriority++;
        }
        ui.prettyPrinting(listings);
        return listings;
    }

    public void initializeHashMap(HashMap<Integer, ArrayList<Task>> map) {
        map.put(1, new ArrayList<Task>());
        map.put(2, new ArrayList<Task>());
        map.put(3, new ArrayList<Task>());
    }

    public String getPriorityString(int priority) {
        if (priority == 1) {
            return "LOW";
        } else if (priority == 2) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }
}
