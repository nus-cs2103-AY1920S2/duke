import java.util.HashMap;

// HashMap to contain the days of week
public class DaysOfWeek {

    HashMap<String, Integer> hm = new HashMap<>();

    public HashMap<String, Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }

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