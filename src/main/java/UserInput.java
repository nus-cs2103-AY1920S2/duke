public class UserInput {
    protected String command;
    protected String[] arguments;

    public UserInput(String input) {
        if (input.contains(" ")) {
            String[] inputs = input.split(" ", 2);
            this.command = inputs[0];
            if (inputs[1].contains(" ")) {
                this.arguments = inputs[1].split(" ");
            } else {
                this.arguments = new String[] {inputs[1]};
            }
        } else {
            this.command = input;
            this.arguments = new String[0];
        }
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }

    public String getArgumentsAsString() {
        return String.join(" ", arguments);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(command);
        if (arguments.length > 0) {
            output.append(" ");
            output.append(String.join(" ", arguments));
        }
        return output.toString();
    }
}