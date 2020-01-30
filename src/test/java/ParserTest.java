import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void RequestListToParserTest() {
        assertTrue(Parser.parse("list") instanceof ShowTasksCommand);
    }
}