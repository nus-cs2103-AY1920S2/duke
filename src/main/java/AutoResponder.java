import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoResponder {
    private final List<Task> taskList;
    private final StringBuilder toPrint;
    static Pattern pDeadline = Pattern.compile("deadline (.+) /by (.+)");
    static Pattern pEvent = Pattern.compile("event (.+) /at (.+)");
    static Pattern pTodo = Pattern.compile("todo (.+)");
    static Pattern pDone = Pattern.compile("done (\\d+)");
    static Pattern pDelete = Pattern.compile("delete (\\d+)");
    static Pattern pEmptyCommand = Pattern.compile("(todo|event|deadline)\\s*$");
    static Pattern pList = Pattern.compile("list\\s*$");


    public AutoResponder() {
        this.taskList = new ArrayList<>();
        this.toPrint = new StringBuilder();
    }

    private AutoResponder(List<Task> taskList, StringBuilder toPrint) {
        this.taskList = taskList;
        this.toPrint = toPrint;
    }

    public AutoResponder printToConsole() {
        System.out.print(toPrint);
        return new AutoResponder(taskList, new StringBuilder());
    }

    public AutoResponder readInput(String input) {
        if (pList.matcher(input).find()) {
            return this.processList();
        } else if (pDone.matcher(input).find()) {
            Matcher m = pDone.matcher(input);
            m.find();
            int index = Integer.parseInt(m.group(1)) - 1;
            return this.markTaskDone(index);
        } else if (pDelete.matcher(input).find()) {
            Matcher m = pDelete.matcher(input);
            m.find();
            int index = Integer.parseInt(m.group(1)) - 1;
            return this.deleteTask(index);
        } else if (pEmptyCommand.matcher(input).find()) { //TODO Change to Exception
            Matcher m = pEmptyCommand.matcher(input);
            m.find();
            throw new IllegalArgumentException("☹ OOPS!!! The description of a " +
                    m.group(1) + " cannot be empty.");
        } else if (pDeadline.matcher(input).find()) {
            Matcher m = pDeadline.matcher(input);
            m.find();
            return this.addDeadline(m.group(1), m.group(2));
        } else if (pEvent.matcher(input).find()) {
            Matcher m = pEvent.matcher(input);
            m.find();
            return this.addEvent(m.group(1), m.group(2));
        } else if (pTodo.matcher(input).find()) {
            Matcher m = pTodo.matcher(input);
            m.find();
            return this.addTodo(m.group(1));
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private AutoResponder processList() {
        StringBuilder sb = new StringBuilder(toPrint);
        for (int i = 1; i <= taskList.size(); ++i) {
            sb.append(i).append(". ").append(taskList.get(i - 1)).append("\n");
        }
        return new AutoResponder(taskList, sb).printToConsole();
    }

    private AutoResponder markTaskDone(int index) {
        StringBuilder sb = new StringBuilder(toPrint);
        List<Task> tl = new ArrayList<>(taskList);
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1) +
                    " does not correspond to task list of size " + taskList.size());
        } else {
            tl.set(index, taskList.get(index).makeCompleted());
            sb.append("Nice! I've marked this task as done:\n\t");
            sb.append(tl.get(index)).append("\n");
        }
        return new AutoResponder(tl, sb).printToConsole();
    }

    private AutoResponder deleteTask(int index) {
        StringBuilder sb = new StringBuilder(toPrint);
        List<Task> tl = new ArrayList<>(taskList);
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Index of " + (index + 1) +
                    " does not correspond to task list of size " + taskList.size());
        } else {
            sb.append("Noted! I've removed this task:\n\t");
            sb.append(tl.get(index)).append("\n");
            tl.remove(index);
        }
        return new AutoResponder(tl, sb).printToConsole();
    }

    private AutoResponder addDeadline(String name, String date) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Deadline(name, date));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder addEvent(String name, String date) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Event(name, date));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder addTodo(String name) {
        List<Task> tl = new ArrayList<>(taskList);
        tl.add(new Todo(name));
        return new AutoResponder(tl, toPrint).taskAdded();
    }

    private AutoResponder taskAdded() {
        StringBuilder sb = new StringBuilder(toPrint);
        sb.append("Got it. I've added this task:\n\t");
        sb.append(taskList.get(taskList.size() - 1));
        sb.append("\nNow you have ").append(taskList.size()).append(" tasks in the list.\n");
        return new AutoResponder(taskList, sb).printToConsole();
    }

}

