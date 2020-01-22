public class Task {
    boolean isCompleted;
    String description;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getTaskInformation() {
        if (this.isCompleted) {
            return "[" + '\u2713' + "] " + this.description;
        } else {
            return "[" + '\u274C' + "] " + this.description;
        }
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }
}
