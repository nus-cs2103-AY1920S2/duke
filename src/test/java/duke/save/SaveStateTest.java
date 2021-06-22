package duke.save;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

/**
 * JUnit test class for {@code SaveState}.
 */
public class SaveStateTest {
    /**
     * Tests the constructor of {@code SaveState}.
     */
    @Test
    public void testConstructor() {
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.getImmutableDeepCopyOfTasks()).thenReturn(new ArrayList<>());
        when(taskListMock.getImmutableDeepCopyOfArchive()).thenReturn(new ArrayList<>());

        new SaveState(taskListMock, "Test command");
        verify(taskListMock, times(1)).getImmutableDeepCopyOfTasks();
        verify(taskListMock, times(1)).getImmutableDeepCopyOfArchive();
        verifyNoMoreInteractions(taskListMock);
    }

    /**
     * Tests the getters of {@code SaveState}.
     */
    @Test
    public void testGetters() {
        TaskList taskListMock = mock(TaskList.class);
        when(taskListMock.getImmutableDeepCopyOfTasks()).thenReturn(new ArrayList<>());
        when(taskListMock.getImmutableDeepCopyOfArchive()).thenReturn(new ArrayList<>());

        SaveState saveState = new SaveState(taskListMock, "Test command");
        assertEquals(saveState.getLastCommand(), "Test command");
        assertEquals(0, saveState.getTasksFromSave().size());
        assertEquals(0, saveState.getArchiveFromSave().size());
        assertNotNull(saveState.getTasksFromSave());
        verify(taskListMock, times(1)).getImmutableDeepCopyOfTasks();
        verify(taskListMock, times(1)).getImmutableDeepCopyOfArchive();
        verifyNoMoreInteractions(taskListMock);
    }
}
