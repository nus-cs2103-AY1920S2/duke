import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testMakeSenseOfUserCommand() {
        assertEquals(Ui.LIST, new Parser().makeSenseOfUserCommand("list"));
        assertEquals(Ui.DONE, new Parser().makeSenseOfUserCommand("done 1"));
        assertEquals(Ui.DELETE, new Parser().makeSenseOfUserCommand("delete 1"));
        assertEquals(Ui.ADD, new Parser().makeSenseOfUserCommand("event read book /at 2019-11-11"));
        assertEquals(Ui.ADD, new Parser().makeSenseOfUserCommand("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"));
    }
}
