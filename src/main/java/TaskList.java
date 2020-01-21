import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    protected TaskList(){
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    protected List<Task> getTasks(){
        return tasks;
    }

    protected Task get(int v){
        return tasks.get(v);
    }

    protected int getSize(){
        return tasks.size();
    }

    protected void addTask(Task t){
        tasks.add(t);
    }

    protected void addTask(int pos, Task t){
        tasks.add(pos, t);
    }

    public void addTask(String[] current, Storage storage) throws DukeException {
        String[] words = Arrays.stream(current).skip(1).toArray(String[]::new);
        String command = current[0];

        if (words.length == 0) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
        if (command.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            int position = 0;
            boolean specifyDate = false;
            for (String w : words) {
                if (w.equals("/by")) {
                    String description = String.join(" ", List.of(words).subList(0, position));
                    String date = List.of(words).stream().skip(position + 1).collect(Collectors.joining(" "));
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
                    String datetime = List.of(words).stream().skip(position + 1).collect(Collectors.joining(" "));
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

        } else {
            Task t = new Task(String.join(" ", words));
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

    public void deleteTask(int value, Storage storage) {
        Task cur = tasks.get(value - 1);
        tasks.remove(cur);
        StringBuilder sb = new StringBuilder();

        if (tasks.size() > 0) {
            for (Task t : tasks) {
                sb.append(t.print() + "\n");
            }
            storage.writeToFile(sb.toString());
        } else{
            try {
                new FileWriter(storage.filePath, false).close();
            } catch (IOException ex) {
                System.out.println("file not cleared");
            }
        }

        ui.taskRemoveSuccess(cur, getSize());


    }
}