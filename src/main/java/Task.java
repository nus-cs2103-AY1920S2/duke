

public class Task {

    private String description;
    protected boolean completed;
    protected int completedCode;

    public Task(String description) {

        this.description = description;
        this.completed = false;
        this.completedCode = 0;

    }

    public void completedTask() {

        this.completed = true;
        this.completedCode = 1;
    }

    public String getDescription() {

        return this.description;
    }

    public String saveToHardDiskFormat() {

        return "";
    }

    @Override
    public String toString() {

        if (this.completed) {
            return ("[\u2713] " + this.description);
        } else {
            return ("[\u2718] " + this.description);
        }

    }

}
