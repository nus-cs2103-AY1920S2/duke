package duke.save;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

/**
 * JUnit test class for {@code SaveStateStack}.
 */
public class SaveStateStackTest {
    /**
     * Tests whether save states are popped from the stack.
     */
    @Test
    public void testPop() {
        SaveStateStack testSaveStateStack = new SaveStateStack();
        assertNull(testSaveStateStack.pop());
        SaveState testSaveState = new SaveState(new TaskList(), "command");
        testSaveStateStack.push(testSaveState);
        assertSame(testSaveState, testSaveStateStack.pop());
        assertNull(testSaveStateStack.pop());
    }

    /**
     * Tests if max save state capacity is enforced.
     */
    @Test
    public void testMaxSaveStateCapacity() {
        SaveStateStack testSaveStateStack = new SaveStateStack();
        SaveState testSaveState = new SaveState(new TaskList(), "command");

        // Push 1 more than maximum save states into stack
        for (int i = 0; i < SaveStateStack.MAX_SAVE_STATE_CAPACITY + 1; i++) {
            testSaveStateStack.push(testSaveState);
        }

        // Pop maximum number of save states out
        for (int i = 0; i < SaveStateStack.MAX_SAVE_STATE_CAPACITY; i++) {
            assertNotNull(testSaveStateStack.pop());
        }

        // Check that stack is empty
        assertNull(testSaveStateStack.pop());
    }

}
