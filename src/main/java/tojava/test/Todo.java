package tojava.test;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public String getTask() {
        return description;
    }

    public String getDate() {
        return "";
    }

    public String getWord() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public String changeDate() {
        return "";
    }

    public String changeTime() {
        return "";
    }

    public String getTime() {
        return "";
    }
}
