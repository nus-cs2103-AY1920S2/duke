import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    @Test
    void parseCommandIncorrectInputExceptionThrown() {
        String[] tests = {
                "", "      ", "blah", "/by", "fsdaf", "exit"
        };

        for (String command: tests) {
            assertThrows(DukeMissingDescriptionException.class, () -> Parser.parse(command));
        }
    }
}
