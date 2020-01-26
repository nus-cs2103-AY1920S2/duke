public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" +super.getStatusIcon() + "] " + super.description;

        return myword;
    }
}

