public class Event extends Task {
    protected String time;
    Event(String todo) {
        super(todo);
        String[] strArr = todo.split("/", 2);
        this.task = strArr[0];
        this.time = strArr[1].substring(2);
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), time);
    }
}
