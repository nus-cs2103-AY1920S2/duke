package duke;

import duke.command.Command;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parse_undoCommand_getUndoCommand() {
        String command = "undo";
        try {
            Optional<Command> undoCommand = Parser.parse(command);
            if (undoCommand.isEmpty()) {
                fail("Command was not returned from Parser");
            }
            if (!(undoCommand.get() instanceof UndoCommand)) {
                fail("Command returned from Parser was not an Undo Command");
            }
        } catch (DukeException e) {
            fail("DukeException was thrown");
        }
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