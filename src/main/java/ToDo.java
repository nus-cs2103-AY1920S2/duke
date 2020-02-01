public class ToDo extends Task{
    String time;

    public ToDo(String todo) {
        super(todo);
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "T , " + doneInt + " , " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
