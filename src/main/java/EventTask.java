import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class EventTask extends Task {
    private String taskDescription;
    private Optional<Date> timing;
    private String inputTiming;

    /**
     * Returns an event task that is a task with a specified timing
     * @param taskDescription the name of the task or some basic description
     * @param timing the time when a task should be done at. Should be of the form "at yyyy-MM-dd"
     * @throws InvalidInputException
     */
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

    /**
     * Returns a string to be stored in a text file to be loaded later on
     * @return a String in the form "event TASKDESCRIPTIN / at yyyy-MM-dd / NUMBERtoINDICATEDONE
     */
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
