import java.util.List;
import java.util.HashMap;

class CommandHandler {
    protected List<Task> tasks;
    protected HashMap<String, Command> commands;
    protected boolean isActive;

    public CommandHandler(List<Task> tasks, HashMap<String, Command> commands) {
        this.tasks = tasks;
        this.commands = commands;
        isActive = true;
    }

    public void executeCmd(String cmd) {
        String cmdWord, arg;
        // Check if command is bye
        if (cmd.equalsIgnoreCase("bye")) {
            isActive = false;
            return;
        }
        int spaceIndex = cmd.indexOf(" ");
        if (spaceIndex == -1) {
            // No spaces found, so must be single-word command
            cmdWord = cmd.toLowerCase();
            arg = "";
        } else {
            // Get first word from the cmd string
            cmdWord = cmd.substring(0, spaceIndex).toLowerCase();
            arg = cmd.substring(spaceIndex + 1);
        }
        if (commands.containsKey(cmdWord)) {
            commands.get(cmdWord).execute(arg, tasks);
        } else {
            commands.get("null").execute(arg, tasks);
        }
        
    }
}