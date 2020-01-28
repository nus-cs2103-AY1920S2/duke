import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * The ParserTest program tests if the parser parses
 * user input correctly.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class ParserTest {

    /**
     * Tests if parser parses user input correctly.
     *
     * @throws Exception as a result of parseUserInput throwing Exception.
     */
    @Test
    public void test() throws Exception {
        Parser parser = new Parser();
        String[] output = parser.parseUserInput("delete 3");
        String[] expected = new String[2];

        expected[0] = "delete";
        expected[1] = "3";
        for (int i = 0; i < 2; i++) {
            assertEquals(expected[i], output[i]);
        }
    }
}
