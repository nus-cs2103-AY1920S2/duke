public class Task {
    public static final String doneSymbol = "\u2714";
    public static final String notDoneYetSymbol = "\u2718";

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone)
            return '[' + doneSymbol + "] " + name;
        else
            return '[' + notDoneYetSymbol + "] " + name;
    }
}
