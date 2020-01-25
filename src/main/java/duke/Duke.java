package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.ui.Ui;
import duke.ui.TextUi;
import duke.commands.CommandHandler;
import duke.exceptions.DukeException;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new TextUi();
        Scanner sc = new Scanner(System.in);
        String filePath = "data/tasks.txt";
        List<Task> tasks = new ArrayList<>();
        ui.showGreeting();
        try {
            tasks = load(filePath);
            ui.showReply("Save file loaded!");
        } catch (FileNotFoundException e) {
            ui.showError("Save file not found!");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        CommandHandler handler = new CommandHandler(tasks, ui);
        while (handler.isActive()) {
            handler.executeCmd(sc.nextLine());
        }
        try {
            save(filePath, tasks);
            ui.showReply("Save Success! See you next time!");
        } catch (IOException e) {
            ui.showError("Save Failure :-(. Try again next time!");
        }
        sc.close();
    }

    private static List<Task> load(String filePath) throws FileNotFoundException, DukeException {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(new File(filePath));
        while (s.hasNextLine()) {
            Task task;
            switch (s.nextLine()) {
            case "todo":
                task = new Todo(s.nextLine());
                break;
            case "deadline":
                task = new Deadline(s.nextLine(), LocalDateTime.parse(s.nextLine()));
                break;
            case "event":
                task = new Event(s.nextLine(), LocalDateTime.parse(s.nextLine()), LocalDateTime.parse(s.nextLine()));
                break;
            default:
                throw new DukeException("Save file corrupt!");
            }
            if (s.nextLine().equals("true")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        s.close();
        return tasks;
    }

    private static void save(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toSaveable() + System.lineSeparator());
        }
        fw.close();
    }

}
