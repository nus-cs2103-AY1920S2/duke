/**
 * Represents a to-do task
 */
public class Todo extends Task {
    Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public static Todo decode(String text) {
        String[] parts = text.split(",");
        boolean decodedIsDone = parts[1].equals("1");
        return new Todo(parts[2], decodedIsDone);
    }

    @Override
    public String encodeTask() {
        int encodedIsDone = this.getIsDone() ? 1 : 0;
        return "T," + encodedIsDone + "," + name;
    }

    @Override
    public Todo complete() {
        return new Todo(this.getName(), true);
    }

    @Override
    public Task setName(String newName) {
        return new Todo(newName, isDone);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[T]" + taskString;
        return result;
    }
}

