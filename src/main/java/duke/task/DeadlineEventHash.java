package duke.task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Deadline event hash.
 * Class to include all the Deadlines and events in a HashMap<>
 * Map the LocalDateTime to the event itself.
 * Then sort all the events
 * For Extra Level 8, not completed for now.
 */
public class DeadlineEventHash {

    /**
     * The Hash map.
     */
    HashMap<String, ArrayList<Task>> hashMap = new HashMap<>();

    /**
     * Instantiates a new Deadline event hash.
     */
    public DeadlineEventHash() {
    }

    /**
     * Gets hash map.
     *
     * @return the hash map
     */
    public HashMap<String, ArrayList<Task>> getHashMap() {
        return hashMap;
    }

    /**
     * Add to hash map.
     *
     * @param l the l
     * @param k the k
     */
    // Add elements in the hashmap
    public void addToHashMap(String l, Task k) {

        // If there is already another element with the same date,
        if (hashMap.containsKey(l)) {
            ArrayList<Task> al = hashMap.get(l);
            al.add(k);
            hashMap.put(l, al);
        }
        // Else there is no element in the same date,
        // Put all in the same date.
        hashMap.put(l, new ArrayList<>());
        ArrayList<Task> al = new ArrayList<>();
        al.sort((x, y) -> x.d1.getHour() - y.d1.getHour());
    }

    /**
     * Gets elements in array list.
     *
     * @param l the l
     * @return the elements in array list
     */
    // Get Elements in the ArrayList
    public ArrayList<Task> getElementsInArrayList(String l) {
        // If there is no elements,
        if (hashMap.get(l).isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> alTask = hashMap.get(l);
            alTask.sort((x, y) -> x.d1.getHour() - y.d1.getHour());
            return alTask;
        }
    }

}
