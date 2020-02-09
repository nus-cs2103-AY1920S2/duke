package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parse_undoCommandForEmptyTasks_dukeException() {
        String command = "undo";
        DukeException exception = assertThrows(DukeException.class, () -> Parser.parse(command));
        String expected = "Nothing to undo...";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void verifyEventInput() {
    }

    @Test
    void verifyDeadlineInput() {
    }

    @Test
    void getDueDate() {
    }

    @Test
    void getDescription() {
    }
}