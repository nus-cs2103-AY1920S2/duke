public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    @Override
    public Task getCopy() {
        ToDo t = new ToDo(this.description);
        if (this.isDone()) {
            t.setDone();
        }
        return t;
    }
}