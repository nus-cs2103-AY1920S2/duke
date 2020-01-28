import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    String description = "desc";
    String by = "2020-01-01";
    Deadline deadline = new Deadline(description, by);

    @Test
    void createdIncompleteTest() {
        assertEquals(deadline.isDone, false, "deadline is initialized to incomplete");
    }

    @Test
    void toDatabaseString() {
        assertEquals(deadline.toDatabaseString(), "D|0|" + description + "|" + by + "\n",
                "testing encoding of default deadline task");
    }
}