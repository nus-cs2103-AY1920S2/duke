import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParserTest {
    @Test
    public void invalidCommandName() {
        assertEquals(false, new Parser("invalid command").parse().isPresent());
        assertEquals(false, new Parser("").parse().isPresent());
    }
    
    @Test
    public void validCommand() {
        assertEquals(true, new Parser("done 1").parse().isPresent());
        assertEquals(true, new Parser("event abc/def /at 2019-12-20").parse().isPresent());
        assertEquals(true, new Parser("deadline efgdef /by 2019-12-20").parse().isPresent());
        assertEquals(true, new Parser("list").parse().isPresent());
        assertEquals(true, new Parser("bye").parse().isPresent());
    }
}

