package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
