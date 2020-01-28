public abstract class Item {
    private String name;
    private boolean done;
    Item(String name) {
        this.name = name;
        this.done = false;
    }
    Item(String name, Boolean done) {
        this.name = name;
        this.done = false;
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
        System.out.println("Nice! I've marked this task as done:\n" + this);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getType();
    public abstract String replace();
}
