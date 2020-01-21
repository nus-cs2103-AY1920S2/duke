public class Todo extends Item {
    String name;
    boolean done;
    Todo (String name) {
        super(name);
        this.done = false;
    }

    public String toString() {
        String temp = "[T]"+ super.toString() + "\n";
        return temp;
    }
}
