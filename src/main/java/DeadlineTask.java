import java.util.Scanner;

public class DeadlineTask extends Task {
    private String taskDescription;
    private String deadline;

    public DeadlineTask(String taskDescription, String deadline) throws InvalidInputException {
        this.taskDescription = taskDescription;
        Scanner sc = new Scanner(deadline);
        if(!sc.next().equals("by")) {
            throw new InvalidInputException(deadline);
        }

        this.deadline = sc.nextLine();
    }

    public String formatToStore() {
        String format = "deadline " + taskDescription + " / by " + deadline + " /";
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
        taskWords += " " + taskDescription + " ( by: " + deadline + ")";

        return taskWords;
    }
}
