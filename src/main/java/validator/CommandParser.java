package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CommandParser {
    public HashMap<String, Integer> commandCodeMapping;

    public CommandParser(HashMap<String, Integer> commandCodeMapping) {
        this.commandCodeMapping = commandCodeMapping;
    }

    /**
     * This function is to verify if command valid type (for later extension) so that main functions don't need to check
     * anymore.
     * This function solely aims for command validity check, for better extension in the future.
     * @param command user command
     * @return
     */
    public Integer getCommandCode(String command) {
        try {
            String firstCommandType = command.split(" ")[0];
            String[] commandSplit = command.split(" ");
            if (command.equals("list")) {
                return this.commandCodeMapping.get("list");
            } else if (firstCommandType.equals("done") && commandSplit.length == 2) {
                return this.commandCodeMapping.get("mark done");
            } else if (firstCommandType.equals("delete") && commandSplit.length == 2) {
                return this.commandCodeMapping.get("delete");
            } else {
                String taskType = command.split(" ", 2)[0];
                if (taskType.equals("todo")) {
                    return this.commandCodeMapping.get("todo");
                } else {
                    String content = command.split(" ", 2)[1];
                    if (taskType.equals("deadline")) {
                        String time = content.split("/by")[1];
                        LocalDate.parse(time.trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        return this.commandCodeMapping.get("deadline");
                    } else if (taskType.equals("event")) {
                        String time = content.split("/at")[1];
                        LocalDate.parse(time.trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        return this.commandCodeMapping.get("event");
                    }
                }
            }
        } catch (Exception e) {
            return this.commandCodeMapping.get("invalid command");
        }
        return this.commandCodeMapping.get("invalid command");
    }

}
