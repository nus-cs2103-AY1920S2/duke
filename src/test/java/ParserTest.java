import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void hasNoArgs_reallyHasNoArgs_returnsTrue() {
        assertTrue(Parser.hasNoArgs("list"));
    }

    @Test
    public void hasNoArgs_hasArgs_returnsFalse() {
        assertFalse(Parser.hasNoArgs("done 1"));
    }
}
