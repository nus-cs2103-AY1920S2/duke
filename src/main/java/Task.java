public class Task {

    protected String description;
    protected boolean isDone;
    protected String type = "-";
    protected int index;

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public void set_Index(int new_index) {
        index = new_index;
    }

    public int get_Index() {
        return index;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
        return temp;
    }
}