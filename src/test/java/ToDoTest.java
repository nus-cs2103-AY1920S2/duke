import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void toStringTest_correctInput_stringDescribingToDoObject() {
        ToDo toDoTest = new ToDo("Read book");
        String expectedResult = "[T][:(] Read book";
        String actualResult = toDoTest.toString();
        assertEquals(expectedResult, actualResult);
    }

}