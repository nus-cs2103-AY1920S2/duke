package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.DukeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String directory = System.getProperty("user.home");
    private String fileName = "tasks.txt";
    private Path path = Paths.get(directory, fileName);
    private ArrayList<Task> tasks;
    private ArrayList<Task> archived;


    public Storage() {

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {}
        }
        tasks = new ArrayList<>();
        archived = new ArrayList<>();
    }

    /**
     * Writes to storage in hard disk to store tasks added.
     *
     * @param content data that is to be written to the file.
     * @throws IOException  If invalid path or file.
     */
    public void writeToFile(String content) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            // exception handling
            System.out.println(e);
        }
    }

    /**
     * Archives current tasks to start anew.
     * @param content of current tasks.
     */
    public void archive(String content) {
        try {
            //create new archive file if doesnt exist
            Path newPath = Paths.get(
                    directory, "archive.txt");
            if(!Files.exists(newPath)) {
                Files.createFile(newPath);
            }
            //write to file
            BufferedWriter writer = Files.newBufferedWriter(newPath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of previously archived tasks
     * @return arraylist of recently archived tasks
     * @throws DukeException if nothing has been previously archived
     */
    public ArrayList<Task> retreive() throws DukeException{
        archived = new ArrayList<>();
        try {
            Path newPath = Paths.get(
                    directory, "archive.txt");
            if(!Files.exists(newPath)) {
                throw new DukeException("nothing has been archived yet!");
            }
            List<String> lines = Files.readAllLines(newPath, Charset.defaultCharset());

            for (int i = 0; i < lines.size(); i++) {
                String curr = lines.get(i);
                String[] data = curr.split(",");

                if (data[0].equals("T")) {
                    Task ntodo = new Todo(data[2]);
                    if (data[1].equals("true")) {
                        ntodo.markAsDone();
                    }
                    archived.add(ntodo);
                } else if (data[0].equals("D")) {
                    Task deadline = new Deadline(data[2], LocalDate.parse(data[3]));
                    if (data[1].equals("true")) {
                        deadline.markAsDone();
                    }
                    archived.add(deadline);
                } else if (data[0].equals("E")) {
                    Task event = new Event(data[2], data[3]);
                    if (data[1].equals("true")) {
                        event.markAsDone();
                    }
                    archived.add(event);
                } else {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new DukeException("IO Exception");
        }
        return archived;
    }


    /**
     * Reads from storage in hard disk to get tasks previously added.
     * @throws IOException  If invalid path or file.
     */
    public ArrayList<Task> readFile() throws DukeException {
        try {
            List<String> lines = Files.readAllLines(path, Charset.defaultCharset());

            for (int i = 0; i < lines.size(); i++) {
                String curr = lines.get(i);
                String[] data = curr.split(",");

                if (data[0].equals("T")) {
                    Task ntodo = new Todo(data[2]);
                    if (data[1].equals("true")) {
                        ntodo.markAsDone();
                    }
                    tasks.add(ntodo);
                } else if (data[0].equals("D")) {
                    Task deadline = new Deadline(data[2], LocalDate.parse(data[3]));
                    if (data[1].equals("true")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                } else if (data[0].equals("E")) {
                    Task event = new Event(data[2], data[3]);
                    if (data[1].equals("true")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                } else {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new DukeException("IO Exception");
        }
        return tasks;
    }
}