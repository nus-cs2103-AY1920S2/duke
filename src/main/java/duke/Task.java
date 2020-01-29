package duke;

public class Task {
    public String name;
    public boolean done;
    public static int count = 0;

    public Task(String name) {

        this.name = name;
    }

    public void setDone() {
        done = true;
    }


}


