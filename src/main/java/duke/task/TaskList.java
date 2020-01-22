/*
 * @author leslieharland
 */

package duke.task;

import duke.DukeException;
import duke.command.Operation;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class TaskList.
 */
public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    /**
     * Instantiates a new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
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
     * Gets the.
     *
     * @param v the v
     * @return the task
     */
    public Task get(int v) {
        return tasks.get(v);
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
     * @param t the t
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Adds the task.
     *
     * @param pos the pos
     * @param t   the t
     */
    public void addTask(int pos, Task t) {
        tasks.add(pos, t);
    }

    /**
     * Adds the task.
     *
     * @param current the current
     * @param storage the storage
     * @throws DukeException the duke exception
     */
    public void addTask(String[] current, Storage storage) throws DukeException {
        String[] words = Arrays.stream(current).skip(1).toArray(String[]::new);
        String command = current[0];

        if (words.length == 0) {
            throw new DukeException("☹ OOPS!!! The description of a "
                    + command + " cannot be empty.");
        }
        if (command.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/by")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String date = List.of(words).stream().skip(position + 1)
                            .collect(Collectors.joining(" "));
                    Task t = new Deadline(description, date);
                    tasks.add(getSize(), t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }

            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                Task t = new Deadline(description, "");
                tasks.add(getSize(), t);
            }

        } else if (command.equalsIgnoreCase(Operation.EVENT.toString())) {
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/at")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String datetime = List.of(words).stream().skip(position + 1)
                            .collect(Collectors.joining(" "));
                    Task t = new EventObj(description, datetime);
                    tasks.add(getSize(), t);
                    specifyDate = true;
                    break;
                } else {
                    position++;
                }

            }

            if (!specifyDate) {
                String description = String.join(" ", words);
                Task t = new EventObj(description, "");
                tasks.add(getSize(), t);
            }

        } else if (command.equalsIgnoreCase(Operation.TODO.toString())) {
            Task t = new Todo(String.join(" ", words));
            tasks.add(getSize(), t);

        }

        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.print() + "\n");
        }
        storage.writeToFile(sb.toString());


        Task t = tasks.get(getSize() - 1);
        ui.taskAddSuccess(t, getSize());
    }

    /**
     * Delete task.
     *
     * @param value   the value
     * @param storage the storage
     */
    public void deleteTask(int value, Storage storage) {
        Task cur = tasks.get(value - 1);
        tasks.remove(cur);
        StringBuilder sb = new StringBuilder();

        if (tasks.size() > 0) {
            for (Task t : tasks) {
                sb.append(t.print() + "\n");
            }

        }
        storage.writeToFile(sb.toString());

        ui.taskRemoveSuccess(cur, getSize());


    }
}