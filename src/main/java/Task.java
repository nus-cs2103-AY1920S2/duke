public class Task {

    public String description;
    public int status = 0;
    public String type;

    public Task (String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public int getStatus() {

        return status;
    }

    public void setStatusDone() {

        status = 1;
    }

    @Override
    public String toString() {

        return description;
    }

    public String getType() {

        return "Weirdly, this is just a normal task.";
    }

    public String getBy() {
        return "This doesn't have a getBy date";
    }
}
