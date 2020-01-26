import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import processor.DukeProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeProcessorTester {
    @Test
    public void processorDisableTester() {
        DukeProcessor p = new DukeProcessor();
        p.disable();
        assertEquals(false, p.isActive());
    }
}
