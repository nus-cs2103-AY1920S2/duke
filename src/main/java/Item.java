/**
 * This class is parent class of other three classes, mark to be done and delete functions are enabled.
 */
public abstract class Item {
    Ui ui = new Ui();
    private String name;
    private boolean done;
    Item(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String toString() {
        String temp = "[";
        if (this.done) {
            temp += "✓";
        } else {
            temp += "✗";
        }
        temp += "] "+this.name;
        return temp;
    }

    public Item markDone() {
        this.done = true;
        ui.markDone(this);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public abstract String replace();
    public abstract String now();
}
