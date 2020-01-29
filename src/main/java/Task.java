public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.strip().equalsIgnoreCase("")) {
            throw new DukeException("Invalid description");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone
                ? "\u2713"
                : "\u2718"); //return tick or X symbols
    }

    public void isDone(String doneStatus) {
        if (doneStatus.equalsIgnoreCase("\u2713]")) {
            this.isDone = true;
        }
        else{
            this.isDone = false;
        }
    }

    public void doneTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}