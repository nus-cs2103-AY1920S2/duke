package logic.parser;

import commons.Index;
import commons.StringUtil;
import logic.parser.exceptions.ParserException;
import tasks.Date;
import tasks.Name;
import tasks.Tag;
import static java.util.Objects.requireNonNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParserException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParserException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParserException("Pawdon me, I think you furgot to include the task number\n"
                    + MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParserException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParserException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String alias} into a {@code alias}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code alias} is invalid.
     */
    public static String parseAlias(String alias) throws ParserException {
        requireNonNull(alias);
        String trimmedAlias = alias.trim();
        boolean t = FriendlierSyntax.isValidAlias(trimmedAlias);
        if (!FriendlierSyntax.isValidAlias(trimmedAlias)) {
            throw new ParserException(FriendlierSyntax.MESSAGE_CONSTRAINTS);
        }
        return trimmedAlias;
    }

    /**
     * Parses a {@code String command} into a {@code command}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code command} is invalid.
     */
    public static String parseCommand(String command) throws ParserException {
        requireNonNull(command);
        String trimmedCommand = command.trim();
        if (!FriendlierSyntax.isValidCommand(trimmedCommand)) {
            throw new ParserException(FriendlierSyntax.MESSAGE_CONSTRAINTS);
        }
        return trimmedCommand;
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code tag} is invalid.
     */
    public static Date parseDate(String date) throws ParserException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParserException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParserException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParserException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParserException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}

