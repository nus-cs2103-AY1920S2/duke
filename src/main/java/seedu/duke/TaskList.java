package seedu.duke;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Contains the task list
 */
public class TaskList {
    static List<Task> tasks;
    protected Storage storage;

    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    protected void addTodo(String desc, String doneStatus) throws IOException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);
        storage.addToStorage(todo);
        printAddToList();
        System.out.println(todo.toString());
        printNumTask();
    }

    protected void addDeadline(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /by |\\|") ;
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineTime = descs[1].trim();
        LocalDate formattedDeadlineTime = null;
        if (isValidDate(deadlineTime)) {
            formattedDeadlineTime = LocalDate.parse(deadlineTime);
        } else {
            throw new InvalidDateException();
        }
        Task deadline = new Deadline(deadlineDesc, formattedDeadlineTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
        storage.addToStorage(deadline);
        printAddToList();
        System.out.println(deadline.toString());
        printNumTask();
    }

    protected void addEvent(String desc, String doneStatus)
            throws InvalidTaskInputException, IOException, InvalidDateException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventTime = descs[1].trim();
        LocalDate formattedEventTime = null;
        if (isValidDate(eventTime)) {
            formattedEventTime = LocalDate.parse(eventTime);
        } else {
            throw new InvalidDateException();
        }
        Task event = new Event(eventDesc, formattedEventTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }

        tasks.add(event);
        storage.addToStorage(event);
        printAddToList();
        System.out.println(event.toString());
        printNumTask();
    }

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public void printList() {
        if (tasks.size() == 0) {
            System.out.println("You currently don't have any task. Start listing now!");
        } else {
            System.out.println("Stop procrastinating. Do it now!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
            }
        }
    }

    public void markTaskAsDone(int index) throws IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        storage.changeToStorage(index);
        System.out.println("Good job! One off your chest!");
        System.out.println(task.toString());
    }

    public void deleteTask(int index) throws IOException {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.deleteInStorage(index);
        printRemoveTask();
        System.out.println(task.toString());
        printNumTask();
    }

    private static void printRemoveTask() {
        System.out.println("Okay, I have removed this task for you:");
    }

    private void printAddToList() {
        System.out.println("Gotcha. Added this to your list:");
    }

    private void printNumTask() {
        String taskWord;
        if (tasks.size() <= 1) {
            taskWord = "task";
        } else {
            taskWord = "tasks";
        }
        System.out.printf("Now you got %d %s in your list!\n", tasks.size(), taskWord);
    }
}
