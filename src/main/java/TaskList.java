import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private static final String SPACE = "    ";
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

    public String addTodo(String[] inputArr) throws NoDescriptionException {
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        String nameOfEvent = combineString(inputArr, 1, arrLength);
        Task newT = new Todo(nameOfEvent);
        tasks.add(newT);
        saveReply += newT.toString();
        return saveReply + "\n" + SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia";
    }

    public String addEvent(String[] inputArr) throws NoDateTimeException {
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        int pointer = findIndex("/at", inputArr);

        if (pointer == -1 || pointer == arrLength - 1) {
            throw new NoDateTimeException();
        }

        String nameOfEvent = combineString(inputArr, 1, pointer);
        String dateTime = combineString(inputArr, pointer + 1, arrLength);
        Task newE = new Event(nameOfEvent, dateTime);
        tasks.add(newE);
        saveReply += newE.toString();
        return saveReply + "\n" + SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia";
    }

    public String addDeadline(String[] inputArr) throws DukeException {
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        int pointer = findIndex("/by", inputArr);

        if (pointer == -1 || pointer == arrLength - 1) {
            throw new NoDateException();
        }

        String nameOfEvent = combineString(inputArr, 1, pointer);
        String date = combineString(inputArr, pointer + 1, arrLength);
        Task newD = new Deadline(nameOfEvent, date);
        this.tasks.add(newD);
        saveReply += newD.toString();
        
        return saveReply + "\n" + SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia";
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
                return "Okcan. I will remove this task:\n" + SPACE + "  " + whichTaskDelete + "\n" + SPACE
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
                reply += (tasks.get(i) + "\n" + SPACE);
            }
            reply += "\n" + SPACE + "I told you save liao loh........";
        } else {
            String dateS = arr[1];
            LocalDate date = LocalDate.parse(dateS, inputFormatter);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if (currentTask instanceof Deadline || currentTask instanceof Event) {
                    if (currentTask.compareDate(date)) {
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n" + SPACE);
                    }
                }
            }
            reply += ("\n" + SPACE + "This are all the tasks with that date");
        }
        return reply;
    }

    public String checkDone(String[] inputArr) {
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        this.tasks.set(taskNo, tasks.get(taskNo).complete());
        return "Okcan, I mark this task as done:\n" + SPACE + tasks.get(taskNo);
    }

    public int size() {
        return this.tasks.size();
    }

    private static int findIndex(String s, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private static String combineString(String[] arr, int start, int end) {
        String ans = "";
        for (int i = start; i < end; i++) {
            ans += arr[i];
            if (i != end - 1) {
                ans += " ";
            }
        }
        return ans;
    }
}