package app.util;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import app.exceptions.InvalidCharacterException;
import app.core.Messages;

/**
 * This class checks for any invalid character written into the input.
 */
public final class InputValidator {
    private static Set<Character> invalidCharacters = new HashSet<>(Arrays.asList(','));

    /**
     * Validates a string against a set of invalid characters.
     * @param input The input to be tested.
     * @throws InvalidCharacterException When the input contains an invalid character
     */
    public static void validate(String input) throws InvalidCharacterException {
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (invalidCharacters.contains(character)) {
                throw new InvalidCharacterException(String.format(Messages.INVALID_CHARACTER_MESSAGE, character));
            }
        }
    }
}