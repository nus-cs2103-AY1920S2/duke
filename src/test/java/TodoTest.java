import static org.junit.Assert.assertEquals;
class TodoTest {
    public static void main(String[] args) {
        Todo todo = new Todo("return book");
        assertEquals(todo.toString().trim(),"[T][✗] return book");
        assertEquals(todo.replace().trim(),"[T][✗] return book");
    }
}
