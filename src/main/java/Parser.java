import java.util.Arrays;
import java.util.regex.Pattern;

public class Parser {
    public String parse(Task task) { // parses task for into string to load into storage
        String parsed;
        TaskType taskType = task.getTaskType();
        switch (taskType) {
            case TODO:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription();
                break;
            case EVENT:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Event) task).getTaskTime();
                break;
            case DEADLINE:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Deadline) task).getTaskTime();
                break;
            default:
                parsed = ""; // TODO add error message, change DukeException
        }
        return parsed;
    }

    public Task parse(String line) { // parses line from tasks.txt into a task
        String[] split = line.split(Pattern.quote("|"));
        switch (split[0]) {
            case "T":
                return new ToDo(split[1], split[2]);
            case "E":
                return new Event(split[1], split[2], split[3]);
            case "D":
                return new Deadline(split[1], split[2], split[3]);
            default:
                System.out.println("Error in parser parse method"); // TODO add error message and DukeException
                return null;
        }
    }


}