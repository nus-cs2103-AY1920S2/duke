package ip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void emptyInputShouldReturnNull() {
        Assertions.assertNull(new Parser().parse(""));
    }
}
