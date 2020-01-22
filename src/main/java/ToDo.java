public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
        done = false;
        count++;

    }

    public String toString() {
        if (done) {
            return ("[T][✓] " + name+"\n");
        }

        return ("[T][✗] " + name+"\n");
    }


}
