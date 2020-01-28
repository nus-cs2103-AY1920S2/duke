public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
        done = false;
        count++;

    }

    public ToDo(String name, boolean done) {
        super(name);
        this.done = done;
        count++;

    }

    public String toString() {
        if (done) {
            return ("[T][✓] " + name+"\n");
        }

        return ("[T][✗] " + name+"\n");
    }


}
