package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Duke {

    private Scanner sc;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private ArrayList<Task> tasks;
    private TaskList taskList;

    public Duke() {
        sc = new Scanner(System.in);
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/duke.txt");
        tasks = new ArrayList<>();
    }

    public String run () {
        String status;
        try {
            tasks = storage.printFileContents();
            status = "Successfully retrieved tasks";
        } catch (FileNotFoundException e) {
            status = "File not found";
        }
        taskList = new TaskList(tasks);
        return status;
    }

    /**
     * Return the response message based on the input command from the user.
     *
     * @param input the input command from the user.
     * @return the response message
     */
    protected String getResponse(String input) {
        String responseMessage;
        String command = parser.parseCommand(input);
        if (command.equals("list")) {
            taskList.listTask();
            responseMessage = taskList.taskMessage;
        } else if (command.equals("done")) {
            String[] indexArr = input.substring(5).split(" ");
            taskList.markTaskDone(indexArr);
            String status = storage.writeToFile(tasks);
            responseMessage = taskList.taskMessage;
            assert status.equals("Successful saved to file") : "Failed to write content";
        } else if (command.equals("delete")) {
            String[] indexArr = input.substring(7).split(" ");
            taskList.deleteTask(indexArr);
            String status = storage.writeToFile(tasks);
            responseMessage = taskList.taskMessage;
            assert status.equals("Successful saved to file") : "Failed to write content";
        } else if (command.equals("todo")) {
            int emptyIndex = input.indexOf(" ");
            if (input.trim().equals("todo"))
                return ui.emptyDescription();
            taskList.addToDo(input.substring(emptyIndex + 1));
            String status = storage.writeToFile(tasks);
            responseMessage = taskList.taskMessage;
            assert status.equals("Successful saved to file") : "Failed to write content";

        } else if (command.equals("deadline")) {
            int emptyIndex = input.indexOf(" ");
            int slashIndex = input.indexOf("/");
            if (input.trim().equals("deadline"))
                return ui.emptyDescription();
            if (slashIndex == -1)
                return ui.emptyDate();
            taskList.addDeadline(input.substring(emptyIndex + 1, slashIndex - 1), LocalDate.parse(input.substring(slashIndex + 4)));
            String status = storage.writeToFile(tasks);
            responseMessage = taskList.taskMessage;
            assert status.equals("Successful saved to file") : "Failed to write content";

        } else if (command.equals("event")) {
            int emptyIndex = input.indexOf(" ");
            int slashIndex = input.indexOf("/");
            if (input.trim().equals("event"))
                return ui.emptyDescription();
            if (slashIndex == -1)
                return ui.emptyDate();
            taskList.addEvent(input.substring(emptyIndex + 1, slashIndex - 1), LocalDate.parse(input.substring(slashIndex + 4)));
            String status = storage.writeToFile(tasks);
            responseMessage = taskList.taskMessage;
            assert status.equals("Successful saved to file") : "Failed to write content";

        } else if (command.equals("find")) {
            String keyword = input.split(" ")[1];
            taskList.findTask(keyword);
            responseMessage = taskList.taskMessage;
        }  else if (command.equals("bye")) {
            responseMessage = ui.sayBye();
        }  else {
            responseMessage = ui.invalidCommand();
        }
        return responseMessage;
    }
}

