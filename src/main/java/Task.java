public class Task {

    public String description;
    public int status = 0;

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


}
