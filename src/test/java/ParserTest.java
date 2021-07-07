import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseTask() throws Exception{
        assertEquals(new Event("testy", LocalDate.parse("2035-10-10")).toString(),
                        Parser.parseTask("event testy /by 2035-10-10").toString());
    }

    @Test
    public void testParseIndex() throws Exception {
        assertEquals(5, Parser.parseIndex("done 5"));
    }
}
