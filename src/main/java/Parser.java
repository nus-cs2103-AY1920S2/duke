import CustomException.DukeException;

import java.time.LocalDate;

/**
 * All static methods for parsing data are contained in this class
 */
public class Parser {
    public Parser() {
    }

    /**
     * Returns a String of the first token of the input by the user
     *
     * @param input the string the user types
     * @return String of first token input by user
     * @throws DukeException if firstWord is not recognised
     */
    public static String getFirstWord(String input) throws DukeException{
        int firstSpaceIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                firstSpaceIndex = i;
                break;
            }
        }

        String firstWord;
        if (firstSpaceIndex == -1) {
            firstWord = input;
        } else {
            firstWord = input.substring(0, firstSpaceIndex);
        }

        if (!firstWord.equals("todo") &&
                !firstWord.equals("deadline") &&
                !firstWord.equals("event") &&
                !firstWord.equals("list") &&
                !firstWord.equals("listname") &&
                !firstWord.equals("listdate") &&
                !firstWord.equals("done") &&
                !firstWord.equals("delete") &&
                !firstWord.equals("bye") &&
                !firstWord.equals("find")) {
            throw new DukeException();
        }
        else {
            return firstWord;
        }
    }

    /**
     * Returns description of the task, given an input and the starting index of the description
     *
     * @param input the string the user types
     * @param start the index of the first character of the description in the string
     * @return description of the task
     * @throws DukeException if getSlash throws when there is no slash in input
     */
    public static String getDescription(String input, int start) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return input.substring(start, slashIndex - 1);
    }

    /**
     * Returns the date in LocalDate format given an input
     *
     * @param input the string the user types
     * @return date in LocalDate format
     * @throws DukeException if getSlash throws when there is no slash in input
     */
    public static LocalDate getDate(String input) throws DukeException {
        int slashIndex = Parser.getSlash(input);
        return LocalDate.parse(input.substring(slashIndex + 1));
    }

    /**
     * Returns the index of the slash in the input given an input
     *
     * @param input the string the user types
     * @return index of the slash which indicates the start of the date
     * @throws DukeException if there is no slash in input
     */
    private static int getSlash(String input) throws DukeException {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }

        throw new DukeException();
    }

    /**
     * Returns the index of the task in the list
     *
     * @param input the string the user types
     * @param type the nature of the command - done, or delete
     * @return int index of the task in the list
     * @throws DukeException if unable to parse from string to int
     */
    public static int getIndex(String input, String type) throws DukeException {
        int startPos;

        if (type.equals("done")) {
            startPos = 5;
        } else {
            startPos = 7;
        }

        try {
            String num = input.substring(startPos);
            return Integer.parseInt(num) - 1;
        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
