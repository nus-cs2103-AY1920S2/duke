import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void stringTest(){
        assertEquals("[T]", new ToDo("eat").getType());
        assertEquals("T|0|eat", new ToDo("eat").saveString());
    }
}
