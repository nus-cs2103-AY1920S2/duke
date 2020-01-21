public class Deadline extends Item {
    String name;
    String time;
    boolean done;
    Deadline(String name, String time) {
        super(name);
        this.time = time;
        this.done = false;
    }

    public String toString() {
        String temp = "[D]" + super.toString() + " (by: "+ time + ")\n";
        return temp;
    }
}
