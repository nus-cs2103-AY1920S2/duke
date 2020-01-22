public class Task {
    public String name;
    public boolean done;
    public static int count = 0;

    public Task(String name) {

        this.name = name;
        done = false;
    }

    public void setDone() {
        done = true;
    }


}


