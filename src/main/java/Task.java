public class Task {
    boolean isCompleted;
    String description;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void printTask() {
        if (this.isCompleted) {
            System.out.println("[" + '\u2713' + "] " + this.description);
        } else {
            System.out.println("[" + '\u274C' + "] " + this.description);
        }
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }
}
