package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {

    private String deadlineDateString;
    private String deadlineTimeString;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadline(String type, String task) throws DukeException{
        super(type, task);


        try {
            String[] deadline = task.substring(task.indexOf("/")).split(" ");

            if (deadline.length != 3) {
                throw new DukeException("dateTime");
            }

            this.deadlineDateString = deadline[1];
            this.deadlineTimeString = deadline[2];
            this.deadlineDate = LocalDate.parse(this.deadlineDateString, dateFormatter);
            this.deadlineTime = LocalTime.parse(this.deadlineTimeString, timeFormatter);

        } catch (Exception e) {
            throw new DukeException("dateTime");
        }

    }

    @Override
    public String toString() {

        String checkmark = "N";

        if (this.isDone == true) {
            checkmark = "Y";
        }

        String output = "[" + this.type + "]" + "[" + checkmark + "] ";

        String task_Name2 = task.substring(task.indexOf(" "), task.indexOf("/") - 1);
        output += task_Name2 + " (by: " + deadlineDate.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + deadlineTime.format(
                            DateTimeFormatter.ofPattern("h:mm a")) + ")";
        return output;
    }
}
