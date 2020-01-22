import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Task {
    static int size = 0;
    protected String description;
    private boolean isDone = false;
    private static final String TO_DO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    @Override
    public String toString() {
        return String.format("%s%s", this.isDone ? "[completed]" : "[uncompleted]", this.description);
    }

    protected void setDone() throws Exception {
        if (isDone) {
            throw new Exception("You might have been mistaken. This task has already been completed.");
        }
        isDone = true;
    }

    protected static int getSize() {
        return size;
    }

    protected static Task generateTask(String input) throws Exception {
        String[] inputAsArray = input.split("\\s");
        String type = inputAsArray[0];
        switch (type.toLowerCase()) {
        case TO_DO: {
            return new TodoTask(inputAsArray);
        }
        case EVENT: {
            return new EventTask(inputAsArray);
        }
        case DEADLINE: {
            return new DeadlineTask(inputAsArray);
        }
        default:
            throw new Exception("You might have not chosen a valid task type! Valid types are events, todos, and deadlines");
        }
    }
}

class TodoTask extends Task {
    public TodoTask(String[] inputArr) throws Exception {
        if (inputArr.length < 2) {
            throw new Exception("Todo tasks must have a non-empty description!");
        }
        size++;
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("todo") ? "" : str.equals("/at") ? "at" : str)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return " TODO" + super.toString();
    }
}

class EventTask extends Task {
    public EventTask(String[] inputArr) {
        size++;
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("event") ? "" : str)
                .collect(Collectors.joining(" "));

    }

    @Override
    public String toString() {
        return " EVENT" + super.toString();
    }
}

class DeadlineTask extends Task {
    public DeadlineTask(String[] inputArr) throws Exception {
        if (inputArr.length < 2) {
            throw new Exception("Deadline tasks must have a non-empty description!");
        }
        size++;
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("deadline") ? "" : str.equals("/by") ? "by" : str)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return " DEADLINE" + super.toString();
    }
}


