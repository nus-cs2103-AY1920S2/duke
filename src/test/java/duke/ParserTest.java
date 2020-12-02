package duke;

import duke.command.Command;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    private HashMap<String, String> commandDelimiter = Parser.commandDelimiter;
    private HashMap<String, String> commandTypeFormatInfo = Parser.commandTypeFormatInfo;

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

    @ParameterizedTest
    @ValueSource(strings = {"event", "event abc", "event birthday party 2020-03-02"})
    void verifyEventInput_missingDelimiter_DukeException(String command) {
        String[] commandWords = command.split("\\s+");
        Exception exception = assertThrows(DukeException.class,
                () -> Parser.verifyEventInput(command, commandWords));
        assertEquals(commandTypeFormatInfo.get("event"),
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"event abc /at", "event /at", "event /at 2020-03-01"})
    void verifyEventInput_invalidFormat_DukeException(String command) {
        String[] commandWords = command.split("\\s+");
        Exception exception = assertThrows(DukeException.class,
                () -> Parser.verifyEventInput(command, commandWords));
        assertEquals(commandTypeFormatInfo.get("event"),
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"deadline", "deadline read book"})
    void verifyDeadlineInput_missingDelimiter_DukeException(String command) {
        String[] commandWords = command.split("\\s+");
        Exception exception = assertThrows(DukeException.class,
                () -> Parser.verifyDeadlineInput(command, commandWords));
        assertEquals(commandTypeFormatInfo.get("deadline"),
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"deadline /by", "deadline read book /by"})
    void verifyDeadlineInput_invalidFormat_DukeException(String command) {
        String[] commandWords = command.split("\\s+");
        Exception exception = assertThrows(DukeException.class,
                () -> Parser.verifyDeadlineInput(command, commandWords));
        assertEquals(commandTypeFormatInfo.get("deadline"),
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"event birthday party /at 2020-03-04",
            "event arcade date /at 2020-03-04",
            "deadline read book /by 2020-03-04",
            "deadline buy Flowers For Algernon /by 2020-03-04"})
    void getDueDate_validInput_extractDueDate(String command) {
        String[] commandWords = command.split("\\s+");
        String output = Parser.getDueDate(command, commandWords);
        assertEquals("2020-03-04", output);
    }

    @ParameterizedTest
    @ValueSource(strings = {"event birthday party /at 2020-03-04",
            "event arcade date /at 2020-03-04"})
    void getDescription_validEventTask_extractEventDescription(String command) {
        int startIndex = "event".length();
        String delimiter = commandDelimiter.get("event");
        int endIndex = command.indexOf(delimiter);
        String expected = (command.substring(startIndex, endIndex)).trim();
        String[] commandWords = command.split("\\s+");
        try {
            String output = Parser.getDescription(command, commandWords);
            assertEquals(expected, output);
        } catch (DukeException e) {
            fail("Exception should not be thrown for getDescription");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"deadline read book /by 2020-03-04",
            "deadline buy Flowers For Algernon /by 2020-03-04"})
    void getDescription_validDeadlineTask_extractDeadlineDescription(String command) {
        int startIndex = "deadline".length();
        String delimiter = commandDelimiter.get("deadline");
        int endIndex = command.indexOf(delimiter);
        String expected = (command.substring(startIndex, endIndex)).trim();
        String[] commandWords = command.split("\\s+");
        try {
            String output = Parser.getDescription(command, commandWords);
            assertEquals(expected, output);
        } catch (DukeException e) {
            fail("Exception should not be thrown for getDescription");
        }
    }
}