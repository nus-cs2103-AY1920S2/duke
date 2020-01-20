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

    @Override
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
