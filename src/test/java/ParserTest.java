import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dukeexception.DukeUnknownInputException;
import dukeexception.DukeMissingDescriptionException;

public class ParserTest {

    @Test
    void todoDescriptionCorrectIn() {
        String in = "todo drink";
        assertEquals("drink", Parser.getTodoDescription(in));
    }

    @Test
    void deadlineParamsWrongIn() {
        String in = "deadline buy chocolate by 9pm";
        assertThrows(DukeUnknownInputException.class, () -> Parser.getDeadlineParams(in));
    }

    @Test
    void eventParamsMissingDescription() {
        String in = "event ";
        assertThrows(DukeMissingDescriptionException.class, () -> Parser.getEventParams(in));
    }
}
