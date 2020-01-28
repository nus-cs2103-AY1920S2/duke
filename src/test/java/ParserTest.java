import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getCommand_notStorage() {
        Parser parse = new Parser("deadline return book /by 2019-10-15", false);
        assertEquals(Command.DEADLINE, parse.getCommand());
    }

    @Test
    public void getCommand_isStorage() {
        Parser parse = new Parser("1 deadline return book /by 2019-10-15", true);
        assertEquals(Command.DEADLINE, parse.getCommand());
    }
}
