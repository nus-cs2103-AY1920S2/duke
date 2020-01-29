import java.util.Scanner;

public class ToDoTask extends Task {
    private String task;

    public ToDoTask(String task) {
        this.task = task;
        //breaks down the taskDescription and adds all words individually to the Hashset
        Scanner taskDesc = new Scanner(task);
        while(taskDesc.hasNext()) {
            String keyword = taskDesc.next();
            wordsInDescription.add(keyword);
        }
    }

    public String formatToStore() {
        String format = "todo " + task + " /";
        if(isDone) {
            format += " 1";
        } else {
            format += " 0";
        }
        return format;
    }

    public String toString() {
        String taskWords =  "[T]";
        if(isDone) {
            taskWords += tick;
        } else {
            taskWords += cross;
        }
        taskWords += " " + task;

        return taskWords;
    }
}
