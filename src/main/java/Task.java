class Task {
    private String description;
    private boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(" %s  %s", this.isDone ? "/" : "X", this.description);
    }

    protected void setDone() {
        isDone = true;
    }

}