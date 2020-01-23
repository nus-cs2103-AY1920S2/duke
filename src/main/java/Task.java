package task;
import java.util.List;
import java.util.ArrayList;	


public abstract class Task {
	protected String commandText;
	protected String description;
	boolean isDone = false;
	protected List<String> remainingTokens;

	public Task(String commandText) {
		this.commandText = commandText;

		//Parse this task description
		String[] tokens = commandText.split(" ");
		StringBuilder builder = new StringBuilder();
		remainingTokens = new ArrayList<String>();

		int start;
		for (start = 1; start < tokens.length; start++) { 
			System.out.println(" this is " + tokens[start] + " and " + this.getSeparator());
			if (tokens[start].equals(this.getSeparator())) {
				this.description = builder.toString();
				break;
			}
			builder.append(tokens[start]).append(" ");
		}

		System.out.println(" this is " + builder.toString());

		remainingTokens = new ArrayList<String>();
		for (start = start + 1; start < tokens.length; start++) {
			remainingTokens.add(tokens[start]);
		}
	}	

	public static TaskType getType(String commandText) {
		String[] tokens = commandText.split(" ");
		System.out.println("length is " + tokens.length);	

		if (tokens.length > 2 && tokens[0].equals("todo")) {
			return TaskType.TODO;
		}

		if (tokens.length > 2 && tokens[0].equals("deadline")) {
			return TaskType.DEADLINE;
		}

		if (tokens.length > 2 && tokens[0].equals("event")) {
			return TaskType.EVENT;
		}

		return TaskType.UNDEFINED;
	}

	public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
    	this.isDone = true;
    }

    public abstract String getSignature();

    public abstract String getSeparator();

    protected String getRemainingTokens() {
    	StringBuilder builder = new StringBuilder();
    	for (String token: remainingTokens) {
    		builder.append(token).append(" ");
    	}
    	return builder.toString();
    }

	@Override 
	public String toString() {
		return (this.getStatusIcon() + this.description); 
	}
}