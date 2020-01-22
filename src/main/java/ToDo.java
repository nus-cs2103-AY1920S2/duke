public class ToDo extends Task {

    public ToDo(int id, String name) {
        super(id, name);
        done = false;
        count++;

    }

    public String toString() {
        if (done) {
            return (id + ".[T][✓] " + name);
        }

        return (id + ".[T][✗] " + name);
    }


}
