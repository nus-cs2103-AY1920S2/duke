class Task {
    private String description;
    private boolean isDone;

     Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Get the description
     String getDescription() {
        return description;
    }

    // Set the task as complete
     void setDone(boolean done) {
        isDone = done;
    }
}