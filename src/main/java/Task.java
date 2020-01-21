class Task {
    
    private String taskDescription;
    private boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[✓] " + taskDescription;
        } else {
            return "[✗] " + taskDescription;
        }
    }
}