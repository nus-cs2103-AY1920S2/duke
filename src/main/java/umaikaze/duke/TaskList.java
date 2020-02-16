/**
 * Where List<Task> is handled
 * Adds, delete, markdone and prints the list
 */

package umaikaze.duke;

import umaikaze.duke.task.Deadline;
import umaikaze.duke.task.Event;
import umaikaze.duke.task.Task;
import umaikaze.duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList(List<Task> loadedList) {
        list = loadedList;
    }

    public String getSizeMessage() {
        return "Nyow you have " + list.size() + " tasks in the wist.";
    }

    private Task getTask(String[] line) throws DukeException {
        Task newTask;
        String cmd = line[0];
        Parser p = new Parser(line);
        switch (cmd) {
        case "deadline":
            newTask = new Deadline(p.description, p.date, p.time);
            break;
        case "event":
            newTask = new Event(p.description, p.date, p.time);
            break;
        case "todo":
            newTask = new Todo(p.description);
            break;
        default:
            throw new DukeException(Message.EXCEPTION_UNKNOWN_COMMAND);
        }
        if (p.description.equals("")) {
            throw new DukeException(Message.EXCEPTION_DESCRIPTION_EMPTY);
        }
        if (!cmd.equals("todo") && p.date == null) {
            throw new DukeException(Message.EXCEPTION_TIMING_NOT_FOUND);
        }
        return newTask;
    }

    /**
     * Adds a new Task object to list, as specified by
     * @param line, a String array already split
     * Returns the Task description in String when successful
     */
    public String addTask(String[] line) throws DukeException{
        Task newTask = getTask(line);
        assert !newTask.getDescription().equals("");
        list.add(newTask);
        return Message.TASK_ADDED + "\n\t"
                + newTask + "\n\t" + getSizeMessage();
    }

    /**
     * @param index starts from 0
     */
    public String markDone(int index) throws DukeException{
        if (index >= list.size() || index < 0) {
            throw new DukeException(Message.EXCEPTION_TASK_OUT_OF_BOUNDS);
        }
        Task task = list.get(index);
        task.markDone();
        return Message.TASK_DONE + "\n\t" + task;
    }

    public String delete(int index) throws DukeException{
        if (index >= list.size() || index < 0) {
            throw new DukeException(Message.EXCEPTION_TASK_OUT_OF_BOUNDS);
        }
        Task task = list.get(index);
        list.remove(task);
        return Message.TASK_DELETED + "\n\t"
                + task + "\n\t" + getSizeMessage();
    }

    private List<Task> find(String[] line) {
        StringBuilder keyString = new StringBuilder("");
        for (int i = 1; i < line.length; i++) {
            if (i != line.length - 1) {
                keyString.append(line[i]).append(" ");
            } else {
                keyString.append(line[i]);
            }
        }
        List<Task> matches = new ArrayList<>(100);
        for (Task task : list) {
            if (task.getDescription().contains(keyString.toString())) {
                matches.add(task);
            }
        }
        return matches;
    }

    private String getBasicListString(List<Task> list) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= list.size(); i++) {
            sb.append(i).append(". ").append(list.get(i - 1));
            if (i != list.size()) {
                sb.append("\n\t");
            }
        }
        return sb.toString();
    }

    public String getFindString(String[] line) throws DukeException{
        if (line.length == 1) {
            throw new DukeException(Message.EXCEPTION_EMPTY_SEARCH_KEYWORDS);
        }
        StringBuilder sb = new StringBuilder(Message.FIND + "\n\t");
        List<Task> findResults = find(line);
        if (findResults.size() == 0) {
            sb.append(Message.EMPTY_SEARCH_RESULT);
        } else {
            sb.append(getBasicListString(findResults));
        }
        return sb.toString();
    }

    public void saveFile(Storage st) throws IOException, DukeException{
        st.saveFile(list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Message.LIST + "\n\t");
        if (list.size() == 0) {
            sb.append(Message.EMPTY_LIST);
        } else {
            sb.append(getBasicListString(list));
        }
        return sb.toString();
    }
}
