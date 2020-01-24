public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean completed){
        super(name, completed);
    }

    @Override
    public Todo complete() {
        return new Todo(this.name, true);
    }

    @Override
    public String storeFormat() {
        return "T|" + completed + "|" + name;
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