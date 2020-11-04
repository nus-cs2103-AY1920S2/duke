import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    void processDescriptionForTodo_emptyDescription_exceptionThrown() {
        Parser parser = new Parser("todo");

        assertThrows(DukeException.class, () -> {
            parser.processDescriptionForTodo();
        });

    }

    @Test
    void processDescriptionForTodo_normalDescription_processedCorrectly() throws DukeException {
        Parser parser = new Parser("todo cs2103 homework");
        assertEquals("cs2103 homework", parser.processDescriptionForTodo());

        Parser parser_2 = new Parser("todo a lot of homework, HELP! :(");
        assertEquals("a lot of homework, HELP! :(", parser_2.processDescriptionForTodo());

        Parser parser_3 = new Parser("todo just trying with random symbols, \u2713");
        assertEquals("just trying with random symbols, \u2713", parser_3.processDescriptionForTodo());
    }

    @Test
    void processDescriptionForEventOrDeadline_emptyDescription_exceptionThrown() {
        Parser parser = new Parser("deadline");
        assertThrows(DukeException.class, () -> {
            parser.processDescriptionForEventOrDeadline();
        });
    }

    @Test
    void processDescriptionForEventOrDeadline_normalDescription_processedCorrectly() throws DukeException {
        Parser parser = new Parser("deadline cs2103 indiv project/by 2020-02-04");
        assertEquals("cs2103 indiv project", parser.processDescriptionForEventOrDeadline());

        Parser parser_2 = new Parser("deadline find valentine's day gift/by 2020-02-14");
        assertEquals("find valentine's day gift", parser_2.processDescriptionForEventOrDeadline());

        Parser parser_3 = new Parser("deadline find present for friend's birthday/by 2020-10-11");
        assertEquals("find present for friend's birthday", parser_3.processDescriptionForEventOrDeadline());
    }
}
