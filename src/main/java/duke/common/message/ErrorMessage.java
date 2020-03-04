package duke.common.message;

import java.util.Random;

public class ErrorMessage {

    public static Random random = new Random();

    public static final String COMMAND_NOT_FOUND_0 = 
        "What's this? You're wasting my time!";

    public static final String COMMAND_NOT_FOUND_1 = "Hi";

    public static final String COMMAND_NOT_FOUND_2 = "You're annoying me.";

    public static final String COMMAND_NOT_FOUND_3 = "Burrrppp!";

    public static final String COMMAND_NOT_FOUND_4 = 
            "Hey! Look over there! There! Not here!";

    public static final String COMMAND_NOT_FOUND_5 = 
            "Has anyone ever told you to read the FANTASTIC manual?";

    public static final String COMMAND_NOT_FOUND_6 = "0111001010011010101001 *Beep*";

    public static final String COMMAND_NOT_FOUND_7 = 
            "Life is like a box of chocolates.";

    public static final String COMMAND_NOT_FOUND_8 = "This statement is false.";

    public static final String COMMAND_NOT_FOUND_9 = "Humans shall be exterminated.";

    public static final String EMPTY_COMMAND = "I have made you speechless.";

    public static final String EMPTY_DATETIME = "A date/time is required.";

    public static final String EMPTY_DESCRIPTION = "A description is required.";

    public static final String EMPTY_INDEX = "A list index is required.";

    public static final String EMPTY_SEARCH = "I find that you are annoying.";

    public static final String INVALID_DATETIME = "The date/time format is invalid.";

    public static final String INVALID_INDEX = "The list index is invalid.";

    public static final String[] ALL_COMMAND_NOT_FOUND_MESSAGES = {
        COMMAND_NOT_FOUND_0,
        COMMAND_NOT_FOUND_1,
        COMMAND_NOT_FOUND_2,
        COMMAND_NOT_FOUND_3,
        COMMAND_NOT_FOUND_4,
        COMMAND_NOT_FOUND_5,
        COMMAND_NOT_FOUND_6,
        COMMAND_NOT_FOUND_7,
        COMMAND_NOT_FOUND_8,
        COMMAND_NOT_FOUND_9,
    };

    /**
     * Gets a message for command not found error.
     * @return A random command not found message.
     */
    public static String getRandomCommandErrorMessage() {
        return ALL_COMMAND_NOT_FOUND_MESSAGES[random.nextInt(10)];
    }
}