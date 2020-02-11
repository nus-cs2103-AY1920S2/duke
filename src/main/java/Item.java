import java.time.LocalDate;

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

    /**
     * Returns a string representing the item.
     */
    public String toString() {
        String temp = "[";
        if (this.done) {
            temp += "\u2713";
        } else {
            temp += "\u2718";
        }
        temp += "] " + this.name;
        return temp;
    }

    /**
     * Returns the item after being marked as done.
     */
    public Item markDone() {
        this.done = true;
        ui.markDone(this);
        return this;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    public abstract String replace();

    public abstract String now();

    public abstract LocalDate getDate();



}
