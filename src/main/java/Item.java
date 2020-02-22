import java.time.LocalDate;

/**
 * This class is parent class of other three classes, mark to be done and delete functions are enabled.
 */
public abstract class Item {
    Ui ui = new Ui();
    private String name;
    private boolean isDone;

    Item(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    /**
     * Returns a string representing the item.
     */
    public String toString() {
        String temp = "[";
        if (this.isDone) {
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
        this.isDone = true;
        ui.markDone(this);
        return this;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public abstract String tobeReplaced();

    public abstract String currentString();

    public abstract String checkString();

    public abstract LocalDate getDate();

}
