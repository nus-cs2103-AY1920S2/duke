public abstract class Item {
    static String space = "     ";
    static String line = space + "____________________________________________________________";
    private String name;
    private boolean done;
<<<<<<< HEAD

    Item(String name, Boolean done) {
=======
    Item(String name, boolean done) {
>>>>>>> 2de8869436177ff5920973b07f499d4da26cf8a7
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
        System.out.println(line + "\n" + space + "Nice! I've marked this task as done:\n" + space + this + "\n" + line);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public abstract String replace();
    public abstract String now();
}
