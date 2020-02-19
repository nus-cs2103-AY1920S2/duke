import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads files as TaskList
 */
public class Storage {
    File saveFile;
    File undoFile;

    /**
     * Creates a Storage object, which helps with saving and loading the list
     * @param filepath path of the savefile
     */
    public Storage(String filepath) {
        this.saveFile = new File(filepath);
        this.undoFile = new File("save_undo.txt");
    }

    private static Task stringToTask(String string) {
        char type = string.charAt(1);
        boolean isDone = string.charAt(4) == '\u2713' ? true: false;
        Task task;
        String desc;
        String date;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (type == 'T') {
            desc = string.substring(7);

            task = new Todo(desc);
        } else if(type == 'D') {
            desc = string.substring(7);
            date = desc.split("\\(by: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(by: ")[0];

            task = new Deadline(desc, LocalDate.parse(date, format));
        } else {
            desc = string.substring(7);
            date = desc.split("\\(date: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(date: ")[0];

            task = new Event(desc, LocalDate.parse(date, format));
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads a list of Tasks from the save file and returns it
     * @return the loaded list of Tasks
     * @throws DukeException if the save file doesn't exist
     */
    public TaskList load() throws DukeException{
        List<Task> taskList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String line = reader.readLine();
            while(line != null) {
                Task task = stringToTask(line);
                taskList.add(task);

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Something's wrong with loading the save file...");
        }

        return new TaskList(taskList);

    }

    /**
     * Saves a list of Tasks onto the save file
     * @param tasks the list of Tasks to save
     * @throws DukeException if the save file doesn't exist
     */
    public void save(TaskList tasks) throws DukeException{
        try {
            undoFile.delete();
            saveFile.renameTo(undoFile);
            //Files.move(saveFile.toPath(), undoFile.toPath());

            PrintWriter pw = new PrintWriter(new FileOutputStream(saveFile));
            for (int i = 0; i < tasks.size(); i++) {
                pw.println(tasks.get(i));
            }
            pw.close();
        } catch (IOException e) {
            throw new DukeException("Something's wrong with saving the save or undo file...");
        }
    }

    public TaskList undo() throws DukeException{
        if (undoFile.exists()) {
            saveFile.delete();
            undoFile.renameTo(saveFile);
            return load();
        } else {
            throw new DukeException("Charmander can only remember one undo!");
        }
    }
}
