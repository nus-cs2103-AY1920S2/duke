public abstract class Item {
    static String space = "     ";
    static String line = space + "____________________________________________________________";
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
        System.out.println(line + "\n" + space + "Nice! I've marked this task as done:\n" + space + this + "\n" + line);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public abstract String replace();
}
