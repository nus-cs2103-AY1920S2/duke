package task;

public class Deadline extends Task {
	private String date;
	private String signature = "deadline";
	private final String separator = "/by";

	public Deadline(String commandText) {
		super(commandText);
	}	

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getSeparator() {
		return this.separator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [D]")
		  .append(super.getStatusIcon())
		  .append(" ")
		  .append(super.description)
		  .append(" (by")
		  .append(super.getRemainingTokens())
		  .append(")");
		return sb.toString();
	}
}