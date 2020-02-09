import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;



public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Initializes Duke with its UI and file and load tasks into storage.
     *
     * @throws IOException if there are file exceptions.
     */
    public Duke() throws IOException {
        ui = new Ui();
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        assert this.storage != null : "storage should be instantiated";
        try {
            tasks = new TaskList(storage.load());
            assert tasks.getSize() != 0 : "list of tasks should not be empty";
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Performs all functionality of Duke chat bot.
     *
     * @throws IOException if there are file exceptions.
     */
    public void run() throws IOException {
        ui.print();

        while (ui.hasNextInput()) {
            try {
                String input = ui.getNextInput();
                Parser p = new Parser(input);
                String command = p.getCommand();
                if (command.equals("bye")) {
                    ui.exit();
                    break;
                } else if (command.equals("list")) {
                    if (tasks.getSize() == 0) {
                        System.out.println("There is no task in your list. Please try again...");
                        continue;
                    }
                    ui.printTasks(tasks);
                } else if (command.equals("done")) {
                    int taskIndex = p.getIndex();
                    ui.acknowledgeDone(tasks, taskIndex);
                    assert tasks.getTask(taskIndex).getStatus() == true : "Task should be marked as done.";
                    storage.save(tasks);
                } else if (command.equals("deadline")) {
                    Deadline deadline = new Deadline(p.getTask(), p.getDate());
                    tasks.add(deadline);
                    assert tasks.getTask(tasks.getSize() - 1).toString()
                            .equals(deadline.toString()) : "Added task should be a deadline task.";
                    ui.acknowledgeDeadline(tasks, deadline);
                    storage.save(tasks);
                } else if (command.equals("todo")) {
                    Todo todo = new Todo(p.getTask());
                    tasks.add(todo);
                    ui.acknowledgeTodo(tasks, todo);
                    storage.save(tasks);
                } else if (command.equals("event")) {
                    Task event = new Event(p.getTask(), p.getDate());
                    tasks.add(event);
                    ui.acknowledgeEvent(tasks, event);
                    storage.save(tasks);
                } else if (command.equals("delete")) {
                    ui.acknowledgeDelete(tasks, p.getIndex());
                    storage.save(tasks);
                } else if (command.equals("find")) {
                    ui.acknowledgeFound(tasks, p.getTask());
                } else {
                    ui.printUnknownCommand();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
