public class Command {
	private String command;
	private String[] tokens;

	public Command(String command) {
		this.command = command;
		tokens = command.split(" ");
	}

	private boolean isAddCommand() {
		if (tokens.length < 3){
			return false;
		}
		if (tokens[0].equals("event")) {
			return true;
		}
		if (tokens[0].equals("todo")) {
			return true;
		}
		if (tokens[0].equals("deadline")) {
			return true;
		}
		return false;
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

		if (this.isAddCommand()) {
			return CommandType.ADD;
		}
		return CommandType.UNDEFINED;
	}
}