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

    public String getCmd() {
        return this.input;
    }
}
