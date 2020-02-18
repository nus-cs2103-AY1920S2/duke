import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    public static void main(String[] args) {
        Todo todo = new Todo("return book");
        assertEquals(todo.toString().trim(),"[T][\u2718] return book");
        assertEquals(todo.tobeReplaced().trim(),"[T][\u2718] return book");
    }
}
