/*
 * @author leslieharland
 */

package duke.task;

import duke.DukeException;
import duke.command.Operation;
import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class TaskList.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;


    /**
     * Instantiates a new task list.
     */
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;

    }

    /**
     * Gets the tasks.
     *
     * @return the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task with its index.
     *
     * @param id the task id
     * @return the task
     */
    public Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds the task.
     *
     * @param t the task
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Adds the task.
     *
     * @param command the current
     * @param storage the storage
     * @throws DukeException the duke exception
     */
    public Task addTask(String command, String[] words, Storage storage) throws DukeException {
        assert (storage != null);

        if (words.length == 0) {
            throw new DukeException("☹ OOPS!!! The description of a "
                    + command + " cannot be empty.");
        }
        Task t = null;
        Operation operation = Operation.valueOf(command.toUpperCase());
        switch (operation) {
        case DEADLINE:
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/by")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String date = List.of(words).stream().skip(position + 1)
                            .collect(Collectors.joining(" "));
                    t = new Deadline(description, date);
                    tasks.add(getSize(), t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }
            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                t = new Deadline(description, "");
                tasks.add(getSize(), t);
            }
            break;

        case EVENT:
            position = 0;
            specifyDate = false;
            for (String w : words) {
                if (w.equals("/at")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String datetime = List.of(words).stream().skip(position + 1)
                            .collect(Collectors.joining(" "));
                    t = new EventObj(description, datetime);
                    tasks.add(getSize(), t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }

            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                t = new EventObj(description, "");
                tasks.add(getSize(), t);
            }
            break;

        case TODO:
            t = new Todo(String.join(" ", words));
            tasks.add(getSize(), t);
            assert (Arrays.stream(Operation.values()).noneMatch(o -> o.name().equals(command)));
            break;

        default:
            break;
        }

        StringBuilder sb = new StringBuilder();
        tasks.forEach(task -> sb.append(task.print()).append("\n"));

        assert (sb.toString().split("\\|").length > 1);
        storage.writeToFile(sb.toString());

        return tasks.get(getSize() - 1);

    }

    /**
     * Delete task.
     *
     * @param id      the task index
     * @param storage storage
     */
    public Task deleteTask(int id, Storage storage) {
        Task cur = tasks.get(id - 1);
        tasks.remove(cur);
        StringBuilder sb = new StringBuilder();

        if (tasks.size() > 0) {
            tasks.forEach(t -> sb.append(t.print()).append("\n"));

        }
        storage.writeToFile(sb.toString());
        return cur;
    }

    /**
     * Mark task as done.
     *
     * @param id the task index
     */
    public Task markTaskAsDone(int id) {
        int taskId = id - 1;
        Task cur = tasks.get(taskId);
        StringBuilder sb = new StringBuilder();

        cur.markAsDone();
        tasks.remove(taskId);
        tasks.add(taskId, cur);
        for (Task t : tasks) {
            sb.append(t.print() + "\n");
        }
        storage.writeToFile(sb.toString());
        return cur;
    }

}
