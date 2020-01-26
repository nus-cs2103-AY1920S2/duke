import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class EventTask extends Task {
    private String taskDescription;
    private Optional<Date> timing;
    private String inputTiming;

    public EventTask(String taskDescription, String timing) throws InvalidInputException{
        this.taskDescription = taskDescription;
        Scanner sc = new Scanner(timing);
        if(!sc.next().equals("at")) {
            throw new InvalidInputException("The timing should start with keyword [at]");
        }
        this.inputTiming = sc.nextLine();
        this.timing = TimeHandler.dateFromString(this.inputTiming);
        if(this.timing.isEmpty()) {
            throw new InvalidInputException("Wrong date format");
        }
    }

    public String formatToStore() {
        String format = "event " + taskDescription + " / at " + this.inputTiming + " /";
        if(isDone) {
            format += " 1";
        } else {
            format += " 0";
        }
        return format;
    }

    public String toString() {
        String taskWords =  "[E]";
        if(isDone) {
            taskWords += tick;
        } else {
            taskWords += cross;
        }
        taskWords += " " + taskDescription + " ( at: " + timing.get() + ")";

        return taskWords;
    }
}
