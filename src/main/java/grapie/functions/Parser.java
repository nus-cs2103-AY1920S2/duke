package grapie.functions;


import grapie.commands.CommandTypes;
import grapie.exceptions.ErrorMsg;
import grapie.exceptions.GrapieExceptions;

public class Parser {
    /**
     * Check if a valid number is inputted in String form.
     *
     * @param numStr the string to be checked.
     * @return Boolean stating if the String is a valid number.
     */
    public static boolean isNumber(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the corresponding command for the user's input.
     * Aims to sort user command into List, Delete, Done and Add.
     * Add consist of everything else, including illegal commands, which will be handled in the Grapie
     * .TaskList class.
     *
     * @param command The user's input.
     * @return Returns a string stating if its a list, done, delete, or add (todo, event, deadline) command.
     */
    public CommandTypes.Commands parseCommand(String command) throws GrapieExceptions {
        command = command.trim();

        if (command.equals("list")) {
            return CommandTypes.Commands.LIST;
        } else {
            //ADD, DONE, DELETE,
            if (command.length() >= 3 && command.substring(0, 3).equals("tag")) {
                if (command.length() <= 4) {
                    throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                } else {
                    command.trim();

                    String[] splittedStr = command.substring(4, command.length()).split("\\s+");

                    if (splittedStr.length != 2) {
                        throw new GrapieExceptions(ErrorMsg.wrongTagFormatError);
                    } else {
                        boolean isANumber = isNumber(splittedStr[0]);
                        if (isANumber) {
                            return CommandTypes.Commands.TAG;
                        } else {
                            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                        }
                    }
                }

            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                if (command.length() <= 5) {
                    //no number behind
                    throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                } else {
                    String strNumberDone = command.substring(5, command.length());
                    strNumberDone.replaceAll("\\s+", ""); //remove all white spaces

                    boolean isANumber = isNumber(strNumberDone);

                    if (isANumber) {
                        return CommandTypes.Commands.DONE;
                    } else {
                        throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                    }
                }
            } else if (command.length() >= 4 && command.substring(0, 4).equals("find")) {
                if (command.length() <= 5) {
                    throw new GrapieExceptions(ErrorMsg.emptyKeywordError);
                } else {
                    return CommandTypes.Commands.FIND;
                }
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                if (command.length() <= 7) {
                    throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                } else {
                    if (!command.substring(6, 7).equals(" ")) {
                        throw new GrapieExceptions(ErrorMsg.noSpaceError);
                    } else {
                        String strNumberDeleted = command.substring(7, command.length());
                        strNumberDeleted.replaceAll("\\s+", ""); //remove all white spaces

                        boolean isANumber = isNumber(strNumberDeleted);
                        if (isANumber) {
                            return CommandTypes.Commands.DELETE;
                        } else {
                            throw new GrapieExceptions(ErrorMsg.invalidNumberError);
                        }
                    }
                }
            } else {
                return CommandTypes.Commands.ADD;
            }
        }
    }
}
