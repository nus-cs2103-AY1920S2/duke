public class Todo extends Task{
    public Todo(String name, boolean completed){
        super(name, completed);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[T]" + doneCheck + this.name;
        } else {
            return "[T]" + notDoneCheck + this.name;
        }
    }
}