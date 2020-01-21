public class Command {
    private String input;

    public Command(String in) {
        this.input = in.toLowerCase();
    }

    public boolean isBye() {
        return this.input.equals("bye");
    }

    public boolean isList() {
        return this.input.equals("list");
    }

    public boolean isDone() {
        if (this.input.length() < 6) {
            return false;
        } else {
            return this.input.substring(0, 4).equals("done");
        }
    }

    public String getCmd() {
        return this.input;
    }
}
