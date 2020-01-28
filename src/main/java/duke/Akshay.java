package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Akshay {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Akshay(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        new Akshay("./data/duke.txt").run();
    }

    public void run() throws IOException {
        TaskList arr = tasks;
        UI.say("Hello I am [AKSHAY]!\nHow may I help you?");
        Scanner sc =  new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] c = input.split(" ", 2);
            switch (c[0]) {
                case ("list"):
                    UI.list(arr);
                    break;
                case ("done"):
                    Task curr = arr.get(Integer.parseInt(c[1]) - 1);
                    curr.mark();
                    UI.done(curr);
                    break;
                case ("todo"):
                    try {
                        Task todo = new Todo(c[1]);
                        arr.add(todo);
                        UI.added(todo);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        UI.say("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case ("deadline"):
                    String[] dl = c[1].split("/by",2);
                    Task d = new Deadline(dl[0], dl[1].trim());
                    arr.add(d);
                    UI.added(d);
                    break;
                case ("event"):
                    String[] ev = c[1].split("/at",2);
                    Task e = new Event(ev[0], ev[1].trim());
                    arr.add(e);
                    UI.added(e);
                    break;
                case ("delete") :
                    try {
                        Task del = arr.get(Integer.parseInt(c[1]) - 1);
                        arr.remove(Integer.parseInt(c[1]) - 1);
                        UI.delete(del);
                    } catch (Exception i) {
                        UI.say("Failed to delete item!!!");
                    }
                    break;
                default:
                    try {
                        throw new DukeException();
                    } catch (DukeException de) {
                        UI.say("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
            }
            storage.save(arr.toArr());
            input = sc.nextLine();
        }
        UI.goodbye();
    }
}