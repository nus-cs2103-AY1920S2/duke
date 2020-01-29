import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class TodoTest {
    @Test
    public void test() {
        Todo todo = new Todo("return book");
        assertEquals(todo.toString().trim(),"[T][✗] return book");
        assertEquals(todo.replace().trim(),"[T][✗] return book");
    }
}
