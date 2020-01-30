public class Item {

    private String name;
    private boolean done;

    public Item(String name) {
        this.name = name;
        this.done = false;
    }

    public Item(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Mark the item as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Formats to vertical bar seperated values.
     * @return The string to be used in file saving.
     */
    public String toSaveFormat() {
        return String.format("%d | %s", done ? 1 : 0, name);
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