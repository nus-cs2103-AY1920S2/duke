import CustomException.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the task list in memory. A <code>TaskList</code> object corresponds to a task list
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> contents) throws DukeException {
        list = new ArrayList<>();
        load(contents);
    }

    /**
     * Parse all information read from the database into memory
     *
     * @param contents
     * @throws DukeException
     */
    private void load(ArrayList<String> contents) throws DukeException {
        try {
            for (String str : contents) {
                char type = str.charAt(0);
                boolean done = Boolean.parseBoolean(str.substring(4, 5));
                String description;
                String byAt;

                // To Do
                if (type == 'T') {
                    description = str.substring(8);
                    list.add(new ToDo(description, done));
                } else {
                    int lastIndex = findThirdStrike(str) - 1;
                    description = str.substring(8, lastIndex);
                    byAt = str.substring(lastIndex + 3);

                    if (type == 'D') {
                        list.add(new Deadline(description, done, LocalDate.parse(byAt)));
                    } else if (type == 'E') {
                        list.add(new Event(description, done, LocalDate.parse(byAt)));
                    }
                }
            }
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public void save(Storage storage) throws IOException {
        storage.save(list);
    }

    private int findThirdStrike(String str) {
        int count = 3;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }

        return -1;
    }

    public void display() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1)
                    + ". "
                    + list.get(i).toString());
        }
    }

    public String displayGUI() {
        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            ret.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }

        return ret.toString();
    }

    public void display(ArrayList<Integer> indices) {
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < indices.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(indices.get(i)).toString());
        }
    }

    public void add(String description, boolean isDone) {
        list.add(new ToDo(description, isDone));
    }

    public void add(char type, String description, boolean isDone, LocalDate date) {
        if (type == 'D') {
            list.add(new Deadline(description, isDone, date));
        } else if (type == 'E') {
            list.add(new Event(description, isDone, date));
        } else {
            System.out.println("Error: type must be 'D' or 'E'");
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void markAsDone(int index) {
        list.get(index).markAsDone();
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    public void find(String key) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String desc = list.get(i).getDescription();
            if (desc.contains(key)) {
                indices.add(i);
            }
        }

        display(indices);
    }
}
