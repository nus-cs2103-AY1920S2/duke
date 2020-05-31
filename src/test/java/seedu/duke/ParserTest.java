package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeEmptyDescriptionException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeWrongCommandException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parse() {
        assertThrows(DukeWrongCommandException.class, () -> Parser.parse("foo"));

        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("todo"));
        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("deadline"));
        assertThrows(DukeEmptyDescriptionException.class, () -> Parser.parse("event"));

        try {
            assertNotNull(Parser.parse("bye"));
            assertNotNull(Parser.parse("list"));
            assertNotNull(Parser.parse("sort"));

            assertNotNull(Parser.parse("done 0"));
            assertNotNull(Parser.parse("delete 0"));

            assertNotNull(Parser.parse("find book"));

            assertNotNull(Parser.parse("todo something this and that"));
            assertNotNull(Parser.parse("deadline something /by 2019-10-15"));
            assertNotNull(Parser.parse("event something else /at 2019-10-15"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}