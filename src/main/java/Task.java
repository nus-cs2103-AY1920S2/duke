public class Task {
    public int id;
    public String name;
    public boolean done;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public String toString() {
        if (done) {
            return (id + ".[✓] " + name);
        }

        return (id + ".[✗] " + name);
    }
}


