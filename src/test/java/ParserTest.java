import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class ParserTest {

    @Test
    public void getCommandTypeTest() throws DukeException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);
        String command = "byebye";
        assertEquals("bye", parser.getCommandType(command));

        command = "haha";
        String finalCommand = command;
        assertThrows(DukeException.class, () -> parser.getCommandType(finalCommand));
    }

    @Test
    public void getTaskNoTest() throws DukeException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);
        String command = "delete 2";
        // Using numbering starting from 0.
        assertEquals(1, parser.getTaskNo(command));
    }
}
