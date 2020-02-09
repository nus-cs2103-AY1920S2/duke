package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalTime;

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
            System.out.println(LocalTime.now());
            if (command.equals("list")) {
                return this.commandCodeMapping.get("list");
            } else if (firstCommandType.equals("done") && commandSplit.length == 2) {
                return this.commandCodeMapping.get("mark done");
            } else if (firstCommandType.equals("delete") && commandSplit.length == 2) {
                return this.commandCodeMapping.get("delete");
            } else if (firstCommandType.equals("find") && commandSplit.length == 2) {
                return this.commandCodeMapping.get("find");
            } else {
                String taskType = command.split(" ", 2)[0];
                if (taskType.equals("todo")) {
                    return this.commandCodeMapping.get("todo");
                } else {
                    if (taskType.equals("deadline")) {
                        ArrayList<String> parsedParams = parseTaskDescTime(command, "/by");
                        LocalDate.parse(parsedParams.get(1).trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        LocalTime.parse(parsedParams.get(2).trim(), DateTimeFormatter.ISO_TIME);
                        return this.commandCodeMapping.get("deadline");
                    } else if (taskType.equals("event")) {
                        ArrayList<String> parsedParams = parseTaskDescTime(command, "/at");
                        LocalDate.parse(parsedParams.get(1).trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        LocalTime.parse(parsedParams.get(2).trim(), DateTimeFormatter.ISO_TIME);
                        return this.commandCodeMapping.get("event");
                    }
                }
            }
        } catch (Exception e) {
            return this.commandCodeMapping.get("invalid command");
        }
        return this.commandCodeMapping.get("invalid command");
    }

    public ArrayList<String> parseTaskDescTime(String command, String seperator) {
        String content = command.split(" ", 2)[1];
        String desc = content.split(seperator)[0];
        String dayTime = content.split(seperator)[1].trim();
        String day = dayTime.split(" ")[0];
        String time = dayTime.split(" ")[1];
        return new ArrayList<String>(Arrays.asList(desc, day, time));
    }

}
