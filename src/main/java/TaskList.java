import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<String> lines) {
        tasks = lines.stream().map(TaskList::decode).collect(Collectors.toList());
    }

    private static Task decode(String str) {
        String[] splitInput = str.split(Pattern.quote(Task.SEPERATOR));
        String taskType = splitInput[0];
        boolean isDone = splitInput[1].equals(Task.TRUE_SYMBOL);
        String taskDescription = splitInput[2];
        LocalDate date = null;
        Task toReturn = null;

        switch (taskType) {
            case Todo.TYPE_SYMBOL:
                toReturn = new Todo(taskDescription);
                break;
            case Deadline.TYPE_SYMBOL:
                date = LocalDate.parse(splitInput[3]);
                toReturn = new Deadline(taskDescription, date);
                break;
            case Event.TYPE_SYMBOL:
                date = LocalDate.parse(splitInput[3]);
                toReturn = new Event(taskDescription, date);
                break;
            default:
                System.out.println("Failed to decode. Unknown task type.");
                break;
        }

        if (isDone && toReturn != null) {
            toReturn.markAsDone();
        }

        return toReturn;
    }

    List<Task> getTasks() {
        return tasks;
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    int size() {
        return tasks.size();
    }

    Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    void add(Task newTask) {
        tasks.add(newTask);
    }

    void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }
}
