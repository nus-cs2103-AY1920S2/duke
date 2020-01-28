public class Todo extends Item {
    String name;
    boolean done;
    Todo (String name) {
        super(name);
        this.done = false;
    }
    Todo (String name, boolean done) {
        super(name, done);
    }

    public String toString() {
        String temp = "   [T]"+ super.toString() + "\n";
        return temp;
    }

    public String replace() {
        String temp = "   [T][✗] " + super.getName() + "\n";
        return temp;
    }
}
