public class Task {
	private String description;
	boolean isDone = false;

	public Task(String description) {
		this.description = description;
	}

	public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
    	this.isDone = true;
    }

	@Override 
	public String toString() {
		return (this.getStatusIcon() + this.description); 
	}
}