package command;

import exception.AelitaException;
import exception.InsufficientArgumentAelitaException;
import main.Response;

/**
 * The abstract class Command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return the response from executing the command.
     * @throws AelitaException if the command failed to execute.
     */
    abstract public Response execute() throws AelitaException;

    /**
     * Checks the array of tokens to ensure that there are at least 2 tokens within it.
     *
     * @param tokens  the array of tokens for checking.
     * @param context the command which called this method.
     * @throws InsufficientArgumentAelitaException if there are less than 2 strings in the token array.
     */
    protected void checkSufficientTokens(String[] tokens, String context) throws InsufficientArgumentAelitaException {

        if (tokens.length < 2) {
            throw new InsufficientArgumentAelitaException(context);
        }
    }

    /**
     * Reconstructs the description into a string from an array of words.
     *
     * @param descriptionTokens An array of words of the description.
     * @return The string form of the description.
     */
    protected String reconstructDescription(String[] descriptionTokens) {

        StringBuilder builder = new StringBuilder(descriptionTokens[1]);

        for (int i = 2; i < descriptionTokens.length; i++) {
            builder.append(" ");
            builder.append(descriptionTokens[i]);
        }
        return builder.toString();
    }

}
