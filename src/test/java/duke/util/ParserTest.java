package duke.util;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    protected Todo todoParserTest = new Todo("test");

    @Test
    public void parseDataFromFileTest() {
        assertEquals("N", new Parser().parseDataFromFile("T , N , test , 2020-02-20\n").getStatusIcon());
    }
}
