package duke;

import java.util.HashMap;

/**
 * The type Days of week.
 */
// HashMap to contain the days of week
public class DaysOfWeek {

    /**
     * The Hashmap associated with the days of the week.
     */
    HashMap<String, Integer> hm = new HashMap<>();

    /**
     * Gets the hashmap.
     *
     * @return the hm
     */
    public HashMap<String, Integer> getHm() {
        return hm;
    }

    /**
     * Sets hashmap.
     *
     * @param hm the hm
     */
    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }

    /**
     * Instantiates a new Days of week.
     */
    public DaysOfWeek() {
        hm.put("SUNDAY", 7);
        hm.put("SUN", 7);
        hm.put("MONDAY", 1);
        hm.put("MON", 1);
        hm.put("TUESDAY", 2);
        hm.put("TUE", 2);
        hm.put("WEDNESDAY", 3);
        hm.put("WED", 3);
        hm.put("THURSDAY", 4);
        hm.put("THUR", 4);
        hm.put("FRIDAY", 5);
        hm.put("FRI", 5);
        hm.put("SATURDAY", 6);
        hm.put("SAT", 6);
    }
}