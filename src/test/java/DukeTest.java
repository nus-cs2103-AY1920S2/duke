import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void ToDoTest(){
        Todo task = new Todo("Find Royce on Bracket Towers");
        assertEquals("[T][\u2718] Find Royce on Bracket Towers", task.toString());
    }

    @Test
    public void EventTest() {
        Event task = new Event("Performance at the Empty Set", LocalDate.parse("2014-05-20"));
        assertEquals("[E][\u2718] Performance at the Empty Set (May 20 2014)", task.toString());
    }
}
