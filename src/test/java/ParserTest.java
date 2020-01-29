import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void addTodoMissingDescriptionException() {
        try {
            Parser parser = new Parser();
            parser.parseUserInput("todo "); // should throw error
            fail(); // should not be reachable
        } catch (DukeException ex) {
            assertEquals("😭 Sorry! Description of a Todo must not be empty.", ex.getMessage());
        }
    }

    @Test
    public void addDeadlineMissingDescriptionDateException() {
        try {
            Parser parser = new Parser();
            parser.parseUserInput("deadline "); // should throw error
            fail(); // should not be reachable
        } catch (DukeException ex) {
            assertEquals("😭 Sorry! Please provide the description and due date.", ex.getMessage());
        }
    }

    @Test
    public void addDeadlineMissingKeywordException() {
        try {
            Parser parser = new Parser();
            parser.parseUserInput("deadline some description"); // should throw error
            fail(); // should not be reachable
        } catch (DukeException ex) {
            assertEquals("😭 Sorry! Make sure to use the '/by' keyword.", ex.getMessage());
        }
    }
}