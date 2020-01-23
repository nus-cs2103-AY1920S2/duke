public class ToDo  extends Task{
    String time;

    public ToDo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
