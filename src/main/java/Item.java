public class Item {

    private String name;
    private boolean done;

    public Item(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Mark the item as done.
     */
    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String tickOrCross = "✗";
        if (done) {
            tickOrCross = "✓";
        }
        return String.format("[%s] %s", tickOrCross, name);
    }
}