import java.util.ArrayList;
import java.util.Arrays;

public class CommandHandler {
    static Void handleTodoCommand(String command, TaskList taskList,
                                  Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        Task newTask = new ToDo(commands.get(1).trim());
        taskList.addTask(newTask);
        ui.printTaskAdded(newTask, taskList.size());
        storage.save(taskList);
        return null;
    }

    static Void handleEventCommand(String command, TaskList taskList,
                                   Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/at")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your event! Please use /at [time here].");
        }
        Task newTask = new Event(details.get(0).trim(), details.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        ui.printTaskAdded(newTask, taskList.size());
        return null;
    }

    static Void handleDeadlineCommand(String command, TaskList taskList,
                                      Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/by")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your deadline! Please use /by [deadline here].");
        }
        String timeDetails = details.get(1).trim().toLowerCase();
        Task newTask = new Deadline(details.get(0).trim(), DateTimeParser.parseDateTime(timeDetails));
        taskList.addTask(newTask);
        storage.save(taskList);
        ui.printTaskAdded(newTask, taskList.size());
        return null;
    }

    static Void handleListCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
        return null;
    }

    static Void handleDoneCommand(String command, TaskList taskList,
                                  Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskCompleted = taskList.completeTask(indexAsInt - 1);
            ui.print("Oh? You actually completed something? Impressive...");
            storage.save(taskList);
            ui.printTask(taskCompleted);
        }
        return null;
    }

    static Void handleDeleteCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskToComplete = taskList.getTask(indexAsInt - 1);
            taskList.removeTask(indexAsInt - 1);
            int size = taskList.size();
            storage.save(taskList);
            ui.printTaskDeleted(taskToComplete, size);
        }
        return null;
    }

    static Void handleByeCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        ui.print("Bye, is it? Shoo shoo then.");
        return null;
    }
}
