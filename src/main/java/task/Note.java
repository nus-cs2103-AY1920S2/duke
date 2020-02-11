package task;

public class Note extends Task {

    public String type;

    public Note(String type, String description) {
        super(description);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
