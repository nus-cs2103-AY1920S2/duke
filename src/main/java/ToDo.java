public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toSaveString() {
        //T1Read a book
        return "T" +
                (isDone ? "1" : "0") +
                name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
