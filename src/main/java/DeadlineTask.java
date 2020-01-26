import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class DeadlineTask extends Task {
    private String taskDescription;
    private Optional<Date> deadline;
    private String inputDeadline;

    public DeadlineTask(String taskDescription, String deadline) throws InvalidInputException  {
        this.taskDescription = taskDescription;
        Scanner sc = new Scanner(deadline);
        if(!sc.next().equals("by")) {
            throw new InvalidInputException("The deadline should start with [by]");
        }
        this.inputDeadline = sc.nextLine();
        this.deadline = TimeHandler.dateFromString(this.inputDeadline);
        if(this.deadline.isEmpty()) {
            throw new InvalidInputException("Wrong date format");
        }
    }

    public String formatToStore() {
        String format = "deadline " + taskDescription + " / by " + inputDeadline + " /";
        if(isDone) {
            format += " 1";
        } else {
            format += " 0";
        }
        return format;
    }

    public String toString() {
        String taskWords =  "[D]";
        if(isDone) {
            taskWords += tick;
        } else {
            taskWords += cross;
        }
        taskWords += " " + taskDescription + " ( by: " + deadline.get() + ")";

        return taskWords;
    }
}
