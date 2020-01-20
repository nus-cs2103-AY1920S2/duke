import java.util.Scanner;

public class EventTask extends Task {
    private String taskDescription;
    private String timing;

    public EventTask(String taskDescription, String timing) throws InvalidInputException {
        this.taskDescription = taskDescription;
        Scanner sc = new Scanner(timing);
        if(!sc.next().equals("at")) {
            throw new InvalidInputException(timing);
        }

        this.timing = sc.nextLine();
    }

    @Override
    public String toString() {
        String taskWords =  "[E]";
        if(isDone) {
            taskWords += tick;
        } else {
            taskWords += cross;
        }
        taskWords += " " + taskDescription + " ( at: " + timing + ")";

        return taskWords;
    }
}
