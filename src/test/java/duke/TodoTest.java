package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals("[T][\u2718] read book", new Todo("read book").toString());
    }
}