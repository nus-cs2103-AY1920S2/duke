import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TranScribeHandler tranScribeHandler;

    public Duke() {
        ui = new Ui();
        tranScribeHandler = new TranScribeHandler();
        storage = new Storage("data/duke.txt");
        //storage = new Storage("duke/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String run(String input) {

        while (!input.toLowerCase().equals("bye")) {
            Parser parser = new Parser(input);
            String command = parser.processCommand();
            if (command.toLowerCase().equals("list")) {
                if (tasks.getTaskListSize() == 0) {
                    return ui.printEmptyListMessage();
                }
                else {
                    return ui.printList(tasks);
                }
            } else if (command.toLowerCase().equals("done")) {
                int index = parser.processIndex();
                tasks.get(index).updateIsCompleted(true);
                return ui.printMarkedAsDone(tasks.get(index));
            } else if (command.toLowerCase().equals("delete")) {
                int index = parser.processIndex();
                String lst = ui.printDelete();

                String completion_symbol = tasks.get(index).getCompletionStatusAsString();
                String task_type = tasks.get(index).getTaskType();
                String description = tasks.get(index).getDescription();
                String line = completion_symbol + " | [" + task_type + "] | " + description + "\n";
                if (task_type.equals("D")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date + "\n";
                }
                else if (task_type.equals("E")) {
                    String date = tasks.get(index).getDate();
                    line = completion_symbol + " | [" + task_type + "] | " + description + " | " + date + "\n";
                }
                tasks.deleteTask(index);
                return lst + line;

            } else if (command.toLowerCase().equals("find")) {
                String keyword = parser.processKeyword();
                return tasks.findTask(keyword);
            } else if (command.toLowerCase().equals("todo")) {
                try {
                    String description = parser.processDescriptionForTodo();
                    tasks.addTodo(description);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[T]";
                    lst = lst + completion_symbol + " " + task_type + " " + description + "\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                } catch (DukeException exception) {
                    return ui.printExceptionMessage(exception);
                }
            } else if (command.toLowerCase().equals("deadline")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForDeadline();
                    tasks.addDeadline(description, date);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[D]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    lst = lst + completion_symbol + " " + task_type + " " + description + "(by: " + formattedDate + ")\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                }
                catch (DukeException exception){
                    return ui.printExceptionMessage(exception);
                }

            } else if (command.toLowerCase().equals("event")) {
                try {
                    String description = parser.processDescriptionForEventOrDeadline();
                    LocalDate date = parser.processDateForEvent();
                    tasks.addEvent(description, date);
                    String lst = ui.printAdd();

                    String completion_symbol = "[\u2718]";
                    String task_type = "[E]";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = date.format(formatter);
                    lst = lst + completion_symbol + " " + task_type + " " + description + "(at: " + formattedDate + ")\n";
                    lst = lst + ui.printNumTasksInList(tasks);
                    return lst;
                }
                catch (DukeException exception) {
                    return ui.printExceptionMessage(exception);
                }
            } else if (command.toLowerCase().equals("notes")) { // all commands to do with notes are prefaced with the word notes.
                String notes_commands = parser.processNotesCommand();
                try {
                    String response = tranScribeHandler.handle(notes_commands);
                    return response;
                }
                catch (DukeException exception) {
                    return ui.printExceptionMessage(exception);
                }
            }

            return "Good to see you, Mr. Yu!"; // means I don't understand
        }

        storage.updateFile(tasks);
        return ui.printGoodbye();
    }

    /**
     * Gets response from run().
     */
    public String getResponse(String input) {
        return this.run(input);
    }
}