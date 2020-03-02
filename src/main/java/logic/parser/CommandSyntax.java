package logic.parser;

import logic.command.Command;
import logic.command.CommandException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class CommandSyntax {
    public HashMap<String, String> syntax = new HashMap<>();
    public HashSet<String> defaultCommands = new HashSet<>();

    /**
     * Instantiates the command syntax which includes default commands and shortcuts for each command.
     */
    public CommandSyntax() {
        String[] commandList = {"alias", "clear", "deadline", "delete", "done", "event", "bye", "find",
                "hello", "help", "list", "tag", "todo"
        };
        defaultCommands.addAll(Arrays.asList(commandList));
        syntax.put("a", "alias");
        syntax.put("b", "bye");
        syntax.put("l", "list");
        syntax.put("dlt", "delete");
        syntax.put("dn", "done");
        syntax.put("c", "clear");
        syntax.put("f", "find");
        syntax.put("t", "todo");
        syntax.put("ddln", "deadline");
        syntax.put("e", "event");
    }


    /**
     * Returns the list of syntax mappings.
     *
     * @return list of friendlier syntax.
     */
    public HashMap<String, String> getSyntax() {
        return this.syntax;
    }

    public boolean isDefaultCommand(String command) {
        return defaultCommands.contains(command);
    }

    /**
     * Checks if command is a default command. If not check if command is an alias.
     *
     * @param command alias input by the user.
     * @return the command which the alias corresponds to.
     */
    public String lookUpCommand(String command) {
        if (isDefaultCommand(command)) {
            return command;
        } else if (syntax.get(command) != null) {
            return syntax.get(command);
        } else {
            return "No such command";
        }
    }

    /**
     * Checls if alias is being used for another command.
     *
     * @param alias alias to be checked.
     * @return true if alias is unique.
     */
    public boolean isAliasUnique(String alias) {
        return !syntax.containsKey(alias);
    }

    /**
     * Adds alias and corresponding command into mappings.
     *
     * @param friendlierSyntax friendlierSyntax.
     */
    public void addFriendlierSyntax(FriendlierSyntax friendlierSyntax) {
        syntax.put(friendlierSyntax.getAlias(), friendlierSyntax.getCommand());
    }
}
