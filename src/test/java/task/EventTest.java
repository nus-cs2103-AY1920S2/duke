package task;

import org.junit.jupiter.api.Test;

import duke.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void storeFormat() {
        Event test = new Event("hello earth", false, "2010/11/03 1800");
        assertEquals("E| |false| |hello earth| |2010/11/03 1800", test.storeFormat());
    }
}

