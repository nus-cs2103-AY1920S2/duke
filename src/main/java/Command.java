public class Command {
	private String command;
	private String[] tokens;

	public Command(String command) {
		this.command = command;
		tokens = command.split(" ");
	}

	public CommandType getType() {
		if (tokens.length == 1 && tokens[0].equals("bye")) {
			return CommandType.BYE;
		}

		if (tokens.length == 1 && tokens[0].equals("list")) {
			return CommandType.LIST;
		}

		if (tokens.length >= 1 && tokens[0].equals("done")) {
			return CommandType.DONE;
		}

		return CommandType.ADD;
	}
}