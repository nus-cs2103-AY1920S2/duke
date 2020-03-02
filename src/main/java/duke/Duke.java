package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor.
     *
     * @param filePath path to file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            System.out.println(UI.showLoadingError());
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("./data/duke.txt");
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }

    /**
     * The run method.
     *
     * @throws IOException exception
     */
    public void run() throws IOException {
        TaskList arr = tasks;
        System.out.println(UI.say("Hello I am [AKSHAY]!\nHow may I help you?"));
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] c = input.split(" ", 2);
            switch (c[0]) {
            case ("list"):
                System.out.println(UI.list(arr));
                break;
            case ("done"):
                Task curr = arr.get(Integer.parseInt(c[1]) - 1);
                curr.mark();
                System.out.println(UI.done(curr));
                break;
            case ("todo"):
                try {
                    Task todo = new Todo(c[1]);
                    arr.add(todo);
                    System.out.println(UI.added(todo));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out
                            .println(UI.say("OOPS!!! The description of a todo cannot be empty."));
                }
                break;
            case ("deadline"):
                String[] dl = c[1].split("/by", 2);
                Task d = new Deadline(dl[0], dl[1].trim());
                arr.add(d);
                System.out.println(UI.added(d));
                break;
            case ("event"):
                String[] ev = c[1].split("/at", 2);
                Task e = new Event(ev[0], ev[1].trim());
                arr.add(e);
                System.out.println(UI.added(e));
                break;
            case ("delete"):
                try {
                    Task del = arr.get(Integer.parseInt(c[1]) - 1);
                    arr.remove(Integer.parseInt(c[1]) - 1);
                    System.out.println(UI.delete(del));
                } catch (Exception i) {
                    System.out.println(UI.say("Failed to delete item!!!"));
                }
                break;
            case ("find"):
                System.out.println(UI.results(tasks.search(c[1])));
                break;
            case ("help"):
                System.out.println(UI.help());
                break;
            default:
                try {
                    throw new DukeException();
                } catch (DukeException de) {
                    System.out.println(
                            UI.say("OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
            }
            storage.save(arr.toArr());
            input = sc.nextLine();
        }
        System.out.println(UI.goodbye());
    }

    /**
     * The getResponse method.
     * @throws IOException exception
     */
    public String getResponse(String input) throws IOException {
        TaskList arr = tasks;
        String[] c = input.split(" ", 2);
        switch (c[0]) {
        case ("list"):
            return (UI.list(arr));
        case ("done"):
            if (c.length < 2) {
                return (UI.say("I'm sorry, you are missing some args!!!"));
            }
            Task curr = arr.get(Integer.parseInt(c[1]) - 1);
            curr.mark();
            storage.save(arr.toArr());
            return (UI.done(curr));
        case ("todo"):
            try {
                Task todo = new Todo(c[1]);
                arr.add(todo);
                storage.save(arr.toArr());
                return (UI.added(todo));
            } catch (ArrayIndexOutOfBoundsException e) {
                return (UI.say("OOPS!!! The description of a todo cannot be empty."));
            }
        case ("deadline"):
            if (c.length < 2) {
                return (UI.say("I'm sorry, you are missing some args!!!"));
            }
            String[] dl = c[1].split("/by", 2);
            Task d = new Deadline(dl[0], dl[1].trim());
            arr.add(d);
            storage.save(arr.toArr());
            return (UI.added(d));
        case ("event"):
            if (c.length < 2) {
                return (UI.say("I'm sorry, you are missing some args!!!"));
            }
            String[] ev = c[1].split("/at", 2);
            Task e = new Event(ev[0], ev[1].trim());
            arr.add(e);
            storage.save(arr.toArr());
            return (UI.added(e));
        case ("delete"):
            try {
                Task del = arr.get(Integer.parseInt(c[1]) - 1);
                arr.remove(Integer.parseInt(c[1]) - 1);
                storage.save(arr.toArr());
                return (UI.delete(del));
            } catch (Exception i) {
                return (UI.say("Failed to delete item!!!"));
            }
        case ("find"):
            if (c.length < 2) {
                return (UI.say("I'm sorry, you are missing some args!!!"));
            }
            return (UI.results(tasks.search(c[1])));
        case ("help"):
            return (UI.help());
        default:
            try {
                throw new DukeException();
            } catch (DukeException de) {
                return (UI.say("I'm sorry, but I don't know what that means!!!"));
            }
        }
    }
}