import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class DeadlineTask extends Task {
    private String taskDescription;
    private Optional<Date> deadline;
    private String inputDeadline;

    /**
     * Returns a task of type deadline which is a task with a due date
     * @param taskDescription the name of task or some description of the task to be done
     * @param deadline should be of the form "by yyyy-MM-dd" else it will throw IvalidInputException
     * @throws InvalidInputException
     */
    public DeadlineTask(String taskDescription, String deadline) throws InvalidInputException  {
        this.taskDescription = taskDescription;

        //breaks down the taskDescription and adds all words individually to the Hashset
        Scanner taskDesc = new Scanner(taskDescription);
        while(taskDesc.hasNext()) {
            String keyword = taskDesc.next();
            wordsInDescription.add(keyword);
        }

        //checks if deadline is of the form "by yyyy-MM-dd"
        Scanner sc = new Scanner(deadline);
        if(!sc.next().equals("by")) {
            throw new InvalidInputException("The deadline should start with [by]");
        }
        this.inputDeadline = sc.nextLine();
        this.deadline = TimeHandler.dateFromString(this.inputDeadline);
        if(this.deadline.isEmpty()) {
            throw new InvalidInputException("Wrong date format. Should be yyyy-MM-dd");
        }
    }

    /**
     * Used for saving the Task into a text file
     * @return a String of the form "deadline NAME / by yyyy-MM-dd / NUMBER indicating whether the task is done
     */
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
