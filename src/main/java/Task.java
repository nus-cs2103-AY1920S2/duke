public class Task {
    private String name;
    Task(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
