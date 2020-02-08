import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();

        String input = ui.scanInput();
        while (!input.toLowerCase().equals("bye")) { // process all the user requests
            Parser parser = new Parser(input);
            String command = parser.processCommand();
            if (command.toLowerCase().equals("list")) {
                if (tasks.getTaskListSize() == 0) {
                    ui.printEmptyListMessage();
                }
                else {
                    ui.printList(tasks);
                }
            } else if (command.toLowerCase().equals("done")) {
                int index = parser.processIndex();
                tasks.get(index).updateIsCompleted(true);
                ui.printMarkedAsDone(tasks.get(index));
            } else if (command.toLowerCase().equals("delete")) {
                int index = parser.processIndex();
                ui.printDelete();

                String completion_symbol = tasks.get(index).getCompletionStatusAsString();
                String task_type = tasks.get(index).getTaskType();
                String description = tasks.get(index).getDescription();
                String line = completion_symbol + " | [" + task_type + "] | " + description;
                if (task_type.equals("D")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date;
                }
                else if (task_type.equals("E")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date;
                }
                System.out.println(line);

                tasks.deleteTask(index);
            } else if (command.toLowerCase().equals("find")) {
                String keyword = parser.processKeyword();
                tasks.findTask(keyword);
            } else if (command.toLowerCase().equals("todo")) {
                try {
                    String description = parser.processDescriptionForTodo();
                    tasks.addTodo(description);
                    ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[T]";
                    System.out.println(completion_symbol + " " + task_type + " " + description);
                    ui.printNumTasksInList(tasks);
                } catch (DukeException exception) {
                    ui.printExceptionMessage(exception);
                }
            } else if (command.toLowerCase().equals("deadline")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForDeadline();
                    tasks.addDeadline(description, date);
                    ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[D]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    System.out.println(completion_symbol + " " + task_type + " " + description + "(by: " + formattedDate + ")");
                    ui.printNumTasksInList(tasks);
                }
                catch (DukeException exception){
                    ui.printExceptionMessage(exception);
                }

            } else if (command.toLowerCase().equals("event")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForEvent();
                    tasks.addEvent(description, date);
                    ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[E]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    System.out.println(completion_symbol + " " + task_type + " " + description + "(at: " + formattedDate + ")");
                    ui.printNumTasksInList(tasks);
                }
                catch (DukeException exception) {
                    ui.printExceptionMessage(exception);
                }
            } else {
                System.out.println("Oops! I did not understand that.");
            }
            input = ui.scanInput();
        }

        storage.updateFile(tasks); // ned to write back to the file
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}