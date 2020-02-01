import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testShowAdd(){
        assertEquals("[\u2718] po ta to",
                    new Task("po ta to").toString());
    }
}
