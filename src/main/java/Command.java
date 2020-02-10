import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class Command {
    private TaskList tl;
    private List<Task> list;
    private Storage storage;

    public Command(TaskList tl) {
        this.tl = tl;
        this.list = tl.getTaskList();
        this.storage = tl.getStorage();
    }

    public static String exit(){
        String out = "Bye. Hope to see you again soon!";
        return out;
    }

    /**
     * String of every item in taskList.
     */
    public String getListString() {
        String out = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : list) {
            out += "     " + i + "." + task + "\n";
            i++;
        }
        return out;
    }

    /**
     * Prints out that task is done, and marks task done.
     * @param in Task in String form
     */
    public String done(String in) {
        String out;
        try {
            int num = Integer.parseInt(in.substring(5));
            Task taskDone = list.get(num - 1);
            taskDone.markDone();
            out = "Nice! I've marked this task as done:\n";
            out +=  "       " + taskDone;
        } catch (IndexOutOfBoundsException e) { // when no int arg provided
            out = "OOPS!!! Done must take a valid number in the range of the task list.";
        } catch (NumberFormatException e) { // when non-int arg provided
            out = "OOPS!!! Done must take a valid integer in the range of the task list.";
        } finally {
            storage.saveTask(list);
        }
        return out;
    }

    /**
     * Deletes task of taskName from taskList.
     * @param taskName Task to be deleted
     */
    public String delete(String taskName) {
        String out;
        try {
            String taskNum = taskName.split(" ", 2)[1];
            Task currTask = list.get(Integer.parseInt(taskNum) - 1);
            list.remove(currTask);
            out = "Noted. I've removed this task:\n" +  currTask + "\nNow you have " + list.size()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            out = "☹ OOPS!!! Please input a valid number in the range of the task list to delete.";
        } catch (NumberFormatException e) { // when non-int arg provided
            out = "OOPS!!! Delete must take a valid integer in the range of the task list.";
        } finally {
            storage.saveTask(list);
        }
        return out;
    }

    /**
     * Adds task of taskName to taskList.
     * @param taskName Task to be added to taskList
     */
    public String add(String taskName) {
        String out;
        try {
            String taskType = taskName.split(" ", 2)[0];
            String taskDesc = taskName.split(" ", 2)[1];
            Task newTask = null;
            if (taskType.equals("todo")) {
                newTask = new ToDo(taskDesc);
            } else if (taskType.equals("deadline")) {
                String[] in = taskDesc.split("/",2);
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
                String[] in = taskDesc.split("/", 2);
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
        }
        return out;
    }

    /**
     * Response when user submits empty input.
     * @return Response for empty user input
     */
    public String emptyInput() {
        String out = "☹ OOPS!!! Please type something here.";
        return out;
    }

    /**
     * Response when user inputs an unknown command.
     * @return Response for unknown input command by user
     */
    public String unknownCommand() {
        String out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return out;
    }

}
