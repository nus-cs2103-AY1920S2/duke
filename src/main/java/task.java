public class task {
    private String status;
    private String Name;

    public task(String name) {
        this.Name = name;
        this.status = "✗";
    }

    public void markDone() {
        this.status = "✓";
    }

    @Override
    public String toString() {
        return "[" + this.status + "] " + this.Name;
    }
}
