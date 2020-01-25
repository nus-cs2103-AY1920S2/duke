package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private String deadline;
    private LocalDateTime parsedDeadline;

    public DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = deadline;
        parseDeadline(deadline);
    }

    public void parseDeadline(String deadline) throws DukeException{
        try {
            this.parsedDeadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch(Exception e) {
            throw new DukeException("You've entered the deadline date incorrectly! Please use \"dd/mm/yyyy HHmm\" for" +
                    " your dates, e.g. 05/12/2020 1800");
        }
    }

    public LocalDateTime getParsedDeadline() {
        return parsedDeadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                parsedDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
