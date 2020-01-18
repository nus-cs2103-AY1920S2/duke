import java.util.Arrays;

public class Task {
    protected String description;
    private boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task makeTask(String taskType, String[] inp) throws InvalidTaskException {
        String description;
        String time;
        switch (taskType) {
            case "Todo":
                description = String.join(" ", Arrays.copyOfRange(inp, 1, inp.length));
                return new Todo(description);
            case "Deadline":
                int byInd = Arrays.asList(inp).indexOf("/by");
                if (byInd > 1) {
                    description = String.join(" ", Arrays.copyOfRange(inp, 1, byInd));
                    time = String.join(" ", Arrays.copyOfRange(inp, byInd, inp.length));
            }
            default:
                throw new InvalidTaskException();
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return (description);
    }

    //...
}