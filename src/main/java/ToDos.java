public class ToDos extends Task {

    public ToDos(String description, Boolean isDone) {
        super(description,isDone);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
