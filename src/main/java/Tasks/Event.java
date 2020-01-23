package task;

public class Event extends Task {
	private String timer;
	private String signature = "event";
	private String sepapartor = "/at";

	public Event(String commandText) {
		super(commandText);
	}

	@Override
	public String getSignature() {
		return this.signature;
	}

	@Override
	public String getSeparator() {
		return this.sepapartor;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  [E]")
		  .append(super.getStatusIcon())
		  .append(super.description)
		  .append(" (by ")
		  .append(super.getRemainingTokens())
		  .append(")");
		return sb.toString();
	}
}