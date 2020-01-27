package dude.component;

import dude.task.Task;
import dude.task.Todo;
import dude.task.Deadline;
import dude.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TextStorage implements IStorage {
    private static final String fileLocation = "data/dude.txt";

    public TaskList restoreSession(IUserInterface ui) {
        TaskList session = new TaskList();
        File data = new File(fileLocation);
        try (Scanner sc = new Scanner(data)) {
            while (sc.hasNext()) {
                String[] entry = sc.nextLine().split("\\|");
                parseEntry(ui, session, entry);
            }
        } catch (FileNotFoundException e) {
            ui.respond("I didn't find any previous session, starting empty");
        }
        return session;
    }

    public void saveSession(IUserInterface ui, TaskList session) {
        try (FileWriter fw = new FileWriter(fileLocation)) {
            for (Task task : session.getAllTasks()) {
                fw.write(task.storeFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            ui.respond("Warning: An error occurred when saving your tasks. "
                    + "Some of your data may have been lost");
        }
    }

    private void parseEntry(IUserInterface ui, TaskList session, String[] entry) {
        try {
            boolean isDone = entry[1].equals("O");
            switch (entry[0]) {
            case "T":
                session.addTask(new Todo(entry[2], isDone));
                break;
            case "D":
                LocalDate by = LocalDate.parse(entry[3]);
                session.addTask(new Deadline(entry[2], by, isDone));
                break;
            case "E":
                LocalDate from = LocalDate.parse(entry[3]);
                LocalDate to = LocalDate.parse(entry[4]);
                session.addTask(new Event(entry[2], from, to, isDone));
                break;
            default:
                throw new ParsingException();
            }
        } catch (DateTimeParseException
                | ArrayIndexOutOfBoundsException
                | ParsingException e) {
            ui.respond("Warning: An error occurred when reading your tasks. "
                    + "Some of your data may have been lost");
        }
    }
}
