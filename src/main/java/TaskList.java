import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private static final String space = "    ";
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> existingTasks) {
        this.tasks = existingTasks;
    }

    public Task getTask(int num) {
        return tasks.get(num);
    }

    public String addTodo(String name) {
        Task newT = new Todo(name);
        this.tasks.add(newT);
        return newT.toString();
    }

    public String addEvent(String name, String dateTime) {
        Task newE = new Event(name, dateTime);
        this.tasks.add(newE);
        return newE.toString();
    }

    public String addDeadline(String name, String date) {
        Task newD = new Deadline(name, date);
        this.tasks.add(newD);
        return newD.toString();
    }

    public String removeTask(String[] inputArr) throws DukeException{
        if (inputArr.length < 2) {
            throw new NoNumberDeleteException();
        } else {
            int taskToDelete = Integer.parseInt(inputArr[1]);
            if (taskToDelete > tasks.size()) {
                throw new NoSuchDeleteException();
            } else {
                String whichTaskDelete = tasks.get(taskToDelete - 1).toString();
                tasks.remove(taskToDelete - 1);
                return "Okcan. I will remove this task:\n" + space + "  " + whichTaskDelete + "\n" + space
                        + "But you still have " + tasks.size() + " task(s) in the list.";
            }
        }
    }

    public String list(String[] arr) throws DateTimeParseException {
        String reply = "";

        if (arr.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += (numbering + ".");
                reply += (tasks.get(i) + "\n" + space);
            }
            reply += "\n" + space + "I told you save liao loh........";
        } else {
            String dateS = arr[1];
            LocalDate date = LocalDate.parse(dateS, inputFormatter);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if (currentTask instanceof Deadline || currentTask instanceof Event) {
                    if (currentTask.compareDate(date)) {
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n" + space);
                    }
                }
            }
            reply += ("\n" + space + "This are all the tasks with that date");
        }
        return reply;
    }

    public String checkDone(String[] inputArr) {
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        this.tasks.set(taskNo, tasks.get(taskNo).complete());
        return "Okcan, I mark this task as done:\n" + space + tasks.get(taskNo);
    }

    public int size() {
        return this.tasks.size();
    }
}