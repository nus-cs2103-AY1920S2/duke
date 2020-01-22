public class Task {
    public int id;
    public String name;
    public boolean done;
    public static int count = 0;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        done = false;
    }

    public void setDone() {
        done = true;
    }


}


