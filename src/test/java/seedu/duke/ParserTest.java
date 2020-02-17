package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeEmptyDescriptionException;
import seedu.duke.exception.DukeWrongCommandException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parse() {
        assertThrows(DukeWrongCommandException.class, () -> Parser.parse("foo"));

        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("todo"));
        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("deadline"));
        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("event"));
    }
}