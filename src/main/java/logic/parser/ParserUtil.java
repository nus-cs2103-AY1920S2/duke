package logic.parser;

import commons.Index;
import commons.StringUtil;
import tasks.Tag;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    //DukeException("Pawdon me, I think you furgot to include the task number.");

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParserException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParserException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParserException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParserException if the given {@code name} is invalid.
     *                         <p>
     *                         public static Name parseName(String name) throws ParseException {
     *                         requireNonNull(name);
     *                         String trimmedName = name.trim();
     *                         if (!Name.isValidName(trimmedName)) {
     *                         throw new ParseException(Name.MESSAGE_CONSTRAINTS);
     *                         }
     *                         return new Name(trimmedName);
     *                         }
     */

    public static String parseName(String name) throws ParserException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (false /*!String.isValidName(trimmedName)*/) {
            throw new ParserException(""/*.MESSAGE_CONSTRAINTS*/);
        }
        return name;
    }

    public static String parseAlias(String alias) throws ParserException {
        requireNonNull(alias);
        String trimmedName = alias.trim();
        if (false /*!String.isValidName(trimmedName)*/) {
            throw new ParserException(""/*.MESSAGE_CONSTRAINTS*/);
        }
        return alias;
    }

    public static String parseCommand(String command) throws ParserException {
        requireNonNull(command);
        String trimmedName = command.trim();
        if (false /*!String.isValidName(trimmedName)*/) {
            throw new ParserException(""/*.MESSAGE_CONSTRAINTS*/);
        }
        return command;
    }

    public static LocalDate parseDate(String dateInput) throws ParserException {
        requireNonNull(dateInput);
        LocalDate date = LocalDate.parse(dateInput);
        if (false /*!String.isValidName(trimmedName)*/) {
            throw new ParserException(""/*.MESSAGE_CONSTRAINTS*/);
        }
        return date;
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

