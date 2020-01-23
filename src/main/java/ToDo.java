public class ToDo extends Task{
    //tasks without any date/time attached to it
    // e.g., visit new theme park
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
