public class Event extends Item {
    String time;

    Event(String name, String time) {
        super(name, false);
        this.time = time;
    }

    Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    public String toString() {
        String temp = "   [E]" + super.toString() + " (at: "+ time + ")\n";
        return temp;
    }

    public String replace() {
        String temp = "   [E][✗] " + super.getName() + " (at: "+ time + ")\n";
        return temp;
    }
    @Override
    public String getType() {
        return "[E]";
    }
}
