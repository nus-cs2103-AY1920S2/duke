public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
