// Class to include all the Deadlines and events in a HashMap<>
// Map the LocalDateTime to the event itself.
// Then sort all the events according to

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Deadline_event_hash {

    HashMap<String, ArrayList<Task>> hashMap = new HashMap<>();

    public Deadline_event_hash() {
    }

    public HashMap<String, ArrayList<Task>> getHashMap() {
        return hashMap;
    }

    // Add elements in the hashmap
    public void addToHashMap(String l, Task k) {

        // If there is already another element with the same date,
        if(hashMap.containsKey(l)) {
            ArrayList<Task> al = hashMap.get(l);
            al.add(k);
            hashMap.put(l, al);
        }
        // Else there is no element in the same date,
        // Put all in the same date.
        hashMap.put(l, new ArrayList<>());
        ArrayList<Task> al = new ArrayList<>();
        al.sort((x,y)-> x.d1.getHour()-y.d1.getHour());
    }

    // Get Elements in the ArrayList
    public ArrayList<Task> getElementsInArrayList(String l) {
        // If there is no elements,
        if(hashMap.get(l).isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> al_task = hashMap.get(l);
            al_task.sort((x,y)-> x.d1.getHour() - y.d1.getHour());
            return al_task;
        }
    }

}
