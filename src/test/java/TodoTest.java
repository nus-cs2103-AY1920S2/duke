import static org.junit.jupiter.api.Assertions.assertEquals;
class TodoTest {
    public static void main(String[] args) {
        Todo todo = new Todo("return book");
        assertEquals(todo.toString().trim(),"[T][✗] return book");
        assertEquals(todo.replace().trim(),"[T][✗] return book");
    }
}
