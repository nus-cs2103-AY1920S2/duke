import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Task {
    static int size = 0;
    protected String description;
    private boolean isDone = false;
    protected String type = "task";
    private static final String TO_DO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    @Override
    public String toString() {
        return String.format("%s%s", this.isDone ? "[completed]" : "[uncompleted]", this.description);
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.isDone ? "T" : "F";
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

    public static Task generateTask(String[] inputAsArray) throws Exception {
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
    protected LocalDateTime time;

    public TodoTask(String[] inputArr) throws Exception {
        this.type = "todo";
        if (inputArr.length < 2) {
            throw new Exception("Todo tasks must have a non-empty description!");
        }
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("todo") ? "" : str.equals("/at") ? "at" : str)
                .collect(Collectors.joining(" "));

        if (!this.description.contains("at")) {
            throw new Exception("Missing @t");
        }
        int lastAt = description.lastIndexOf(" at ");
        if (lastAt == -1) {
            throw new Exception("Keyword \"by\" missing");
        } else {
            try {
                this.time = LocalDateTime.parse(this.description.substring(lastAt + 4),
                        DateTimeFormatter.ofPattern("yyyy-LL-dd HHmm"));
            } catch (DateTimeParseException e) {
                try {
                    this.time = LocalDateTime.parse(this.description.substring(lastAt + 4),
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
                } catch (DateTimeParseException e2) {
                    throw new Exception("Error: unable to decipher date & time input.");
                }
            }
            this.description = this.description.substring(0, lastAt + 3) + ' '
                    + time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));

        }
    }

    @Override
    public String toString() {
        return " TODO" + super.toString();
    }
}

class EventTask extends Task {
    public EventTask(String[] inputArr) {
        this.type = "event";
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
    protected LocalDateTime time;

    public DeadlineTask(String[] inputArr) throws Exception {
        this.type = "deadline";
        if (inputArr.length < 2) {
            throw new Exception("Deadline tasks must have a non-empty description!");
        }
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("deadline") ? "" : str.equals("/by") ? "by" : str)
                .collect(Collectors.joining(" "));
        int lastBy = description.lastIndexOf(" by ");
        if (lastBy == -1) {
            throw new Exception("Keyword \"by\" missing");
        } else {
            try {
                this.time = LocalDateTime.parse(this.description.substring(lastBy + 4),
                        DateTimeFormatter.ofPattern("yyyy-LL-dd HHmm"));

            } catch (DateTimeParseException e) {
                try {
                    this.time = LocalDateTime.parse(this.description.substring(lastBy + 4),
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
                } catch (DateTimeParseException e2) {
                    throw new Exception("Error: unable to decipher date & time input.");
                }
            }
            this.description = this.description.substring(0, lastBy + 3) + ' '
                    + time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        }
    }
    @Override
    public String toString() {
        return " DEADLINE" + super.toString();
    }
}


