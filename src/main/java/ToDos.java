public class ToDos extends Task {
    String i;

    public ToDos(String todo) {
        super(todo);
    }

    public ToDos(String todo, String i) {
        super(todo);
        if (i.equals("1")) {
            this.doneStatus = true;
        } else {
            this.doneStatus = false;
        }
    }

    @Override
    public String save() {
        int myInt = doneStatus ? 1 : 0;
        return "T , " + myInt + " , " + taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
