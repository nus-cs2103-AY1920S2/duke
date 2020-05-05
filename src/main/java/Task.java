public abstract class Task {
    String name;
    boolean isDone;

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public abstract Task complete();

    public abstract String encodeTask();

    public abstract Task setName(String newName);

    @Override
    public String toString() {
        String checkbox = "[" + (this.getIsDone() ? "\u2713" : "\u2718") + "]";
        String result = checkbox + " " + this.getName();
        return result;
    }
}
