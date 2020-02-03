public class task {
    private String status;
    private String Name;

    public task(String name) {
        this.Name = name;
        this.status = "✗";
    }

    public String getName() {
        return this.Name;
    }

    public boolean isDone() {
        if (this.status.equals("✗")) {
            return false;
        } else {
            return true;
        }
    }

    public void markDone() {
        this.status = "✓";
    }

    public String toSave() {
        String s = "";
        return s;
    }

    @Override
    public String toString() {
        return "[" + this.status + "] " + this.Name;
    }
}
