public class ToDo extends task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toSave() {
        if (this.isDone()) {
            return "T /n 1 /n " + this.getName();
        } else {
            return "T /n 0 /n " + this.getName();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
