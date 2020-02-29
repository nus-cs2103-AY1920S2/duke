package duketasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringNotDoneTest() {
        String feedCat = "feed cat";
        Todo notDoneTodo = new Todo(feedCat);

        String ans = "[T][X] feed cat";
        assertEquals(ans, notDoneTodo.toString());
    }

    public void toStringDoneTest() {
        String feedDog = "feed dog";
        Todo doneTodo = new Todo(feedDog);
        doneTodo.done();
        String ans = "[T][O] feed dog";
        assertEquals(ans, doneTodo.toString());
    }
}
