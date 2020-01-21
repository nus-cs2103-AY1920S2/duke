public class Event extends Item {
    String name;
    String time;
    boolean done;
    Event(String name, String time) {
        super(name);
        this.time = time;
        this.done = false;
    }

    public String toString() {
        String temp = "[E]" + super.toString() + " (at: "+ time + ")\n";
        return temp;
    }
}
