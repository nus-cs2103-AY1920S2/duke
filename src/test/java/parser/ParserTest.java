package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import exception.DukeException;



public class ParserTest {
    @Test
    public void parseTask() {
        try {
            String deadlineType = Parser.getType("deadline return book /by 19-12-2019 4pm");
            assertEquals("deadline", deadlineType);
            String deadlineContent = Parser.getContent("return book /by 19-12-2019 4pm");
            assertEquals("return book", deadlineContent);
            String deadlineDateTime = Parser.getDateTime("deadline return book /by 19-12-2019 4pm", "/by");
            assertEquals("19-12-2019 4pm", deadlineDateTime);

            String eventType = Parser.getType("event project meeting /at 02/11/2020 2359");
            assertEquals("event", eventType);
            String eventContent = Parser.getContent("project meeting /at 02/11/2020 2359");
            assertEquals("project meeting", eventContent);
            String eventDateTime = Parser.getDateTime("event project meeting /at 02/11/2020 2359", "/at");
            assertEquals("02/11/2020 2359", eventDateTime);
        } catch (DukeException err ) {
        }
    }
}
