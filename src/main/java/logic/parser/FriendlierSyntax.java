package logic.parser;

import static commons.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Tasks's Alias in the taskList.
 * Guarantees: immutable; is valid as declared in {@link #isValidFriendlierSyntax(String)}
 */
public class FriendlierSyntax {
    public static final String MESSAGE_CONSTRAINTS =
            "Alias should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    public final String alias;
    public final String command;

    /**
     * Constructs a {@code Alias}.
     *
     * @param alias A valid Alias.
     */
    public FriendlierSyntax(String alias, String command) {
        requireNonNull(alias);
        checkArgument(isValidFriendlierSyntax(alias), MESSAGE_CONSTRAINTS);
        this.alias = alias;
        this.command = command;
    }

    public String getAlias() {
        return alias;
    }

    public static boolean isValidAlias(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidCommand(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getCommand() {
        return command;
    }

    /**
     * Returns true if a given string is a valid Alias.
     */
    public static boolean isValidFriendlierSyntax(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return alias;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendlierSyntax // instanceof handles nulls
                && alias.equals(((FriendlierSyntax) other).alias)); // state check
    }

    @Override
    public int hashCode() {
        return alias.hashCode();
    }
}

