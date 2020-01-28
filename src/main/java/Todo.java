public class Todo extends Task {
    public Todo(String d){
        super(d);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String generateWriteFormat() {
        int k = this.isDone ? 1 : 0;
        return "D|"+ k + "|" + description;
    }
}
