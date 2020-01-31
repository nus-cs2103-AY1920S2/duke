import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run () throws IOException {
        ui.print();

        while (ui.hasNextInput()) {
            try {
                String input = ui.getNextInput();
                Parser p = new Parser(input);
                if (p.getCommand().equals("bye")) {
                    ui.exit();
                    break;
                } else if (p.getCommand().equals("list")) {
                    if (tasks.getSize() == 0) {
                        System.out.println("There is no task in your list. Please try again...");
                        continue;
                    }
                    ui.printTasks(tasks);
                } else if (p.getCommand().equals("done")) {
                    ui.acknowledgeDone(tasks, p.getIndex(tasks));
                    storage.save(tasks);
                } else if (p.getCommand().equals("deadline")) {
                    Deadline deadline = new Deadline(p.getTask(), p.getDate());
                    tasks.add(deadline);
                    ui.acknowledgeDeadline(tasks, deadline);
                    storage.save(tasks);
                } else if (p.getCommand().equals("todo")) {
                    Todo todo = new Todo(p.getTask());
                    tasks.add(todo);
                    ui.acknowledgeTodo(tasks, todo);
                    storage.save(tasks);
                } else if (p.getCommand().equals("event")) {
                    Task event = new Event(p.getTask(), p.getDate());
                    tasks.add(event);
                    ui.acknowledgeEvent(tasks, event);
                    storage.save(tasks);
                } else if (p.getCommand().equals("delete")) {
                    ui.acknowledgeDelete(tasks, p.getIndex(tasks));
                    storage.save(tasks);
                } else if (p.getCommand().equals("find")) {
                    ui.acknowledgeFound(tasks, p.getTask());
                }   else {
                    ui.printUnknownCommand();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}