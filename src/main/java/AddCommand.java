import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddCommand extends Command {

    public AddCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() {
        String out;
        try {
            String taskType = taskDesc.split(" ", 2)[0];
            String taskName = taskDesc.split(" ", 2)[1];
            Task newTask = null;
            if (taskType.equals("todo")) {
                newTask = new ToDo(taskName);
            } else if (taskType.equals("deadline")) {
                String[] in = taskName.split("/",2);
                String task = in[0].trim();
                String dateAndPreposition = in[1];
                String[] res = dateAndPreposition.split(" ", 2);
                String preposition = res[0];
                String dateTime = res[1];
                String[] parseDateTime = dateTime.split(" ",  2);
                LocalDate localDate = LocalDate.parse(parseDateTime[0]);
                if (parseDateTime.length > 1) {
                    String time = parseDateTime[1];
                    newTask = new Deadline(task, preposition, localDate, time);
                } else {
                    newTask = new Deadline(task, preposition, localDate);
                }
            } else { //case when taskType is "event"
                String[] in = taskName.split("/", 2);
                String task = in[0].trim();
                String dateAndPreposition = in[1];
                String[] res = dateAndPreposition.split(" ", 2);
                String preposition = res[0];
                String dateTime = res[1];
                String[] parseDateTime = dateTime.split(" ",  2);
                LocalDate localDate = LocalDate.parse(parseDateTime[0]);
                if (parseDateTime.length > 1) {
                    String time = parseDateTime[1];
                    newTask = new Event(task, preposition, localDate, time);
                } else {
                    newTask = new Event(task, preposition, localDate);
                }
            }

            if (Objects.isNull(newTask)) {
                out = "Attempting to add invalid task. Operation aborted.";
            } else {
                list.add(newTask);
                out = "Got it. I've added this task:\n" + newTask + "\n" + "Now you have "
                        + list.size() + " tasks in the list.";
            }
        } catch (DateTimeParseException e) {
            out = "Error parsing date and time. Please input date and time as YYYY-mm-dd hh:mm";
        } catch (IndexOutOfBoundsException e) {
            out = "☹ OOPS!!! The description of a task cannot be empty.";
        } finally {
            storage.saveTask(list);
            stats.add(this);
            statStorage.saveStats(stats);
        }
        return out;
    }
}
