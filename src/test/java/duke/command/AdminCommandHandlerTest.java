package duke.command;

import static duke.util.MagicStrings.ERROR_NO_MORE_UNDOS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;
import duke.save.SaveState;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * JUnit test class for {@code AdminCommandHandler}.
 */
public class AdminCommandHandlerTest {
    // Dummy variables
    private final TaskList dummyTaskList = new TaskList();
    private final SaveStateStack dummySaveStateStack = new SaveStateStack();
    private final Ui dummyUi = new Ui();


    // Mock storage - preferred since this will not ever write to directory
    private final Storage dummyStorageMock = mock(Storage.class);

    /**
     * Tests the handling of the help command.
     */
    @Test
    public void testHandleHelpCommand() {
        // Mocking the Ui
        Ui uiMock = mock(Ui.class);
        when(uiMock.printHelpMessage()).thenReturn("Help message.");

        String command = "help";

        // Testing
        assertEquals("Help message.", AdminCommandHandler.handleHelpCommand(command, dummyTaskList, uiMock,
                dummyStorageMock, dummySaveStateStack));
        verify(uiMock, times(1)).printHelpMessage();
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the handling of the bye command.
     */
    @Test
    public void testHandleByeCommand() {
        // Mocking the Ui
        Ui uiMock = mock(Ui.class);
        when(uiMock.printGoodbye()).thenReturn("Goodbye.");

        String command = "bye";

        // Testing
        assertEquals("Goodbye.", AdminCommandHandler.handleByeCommand(command, dummyTaskList, uiMock,
                dummyStorageMock, dummySaveStateStack));
        verify(uiMock, times(1)).printGoodbye();
        verifyNoMoreInteractions(uiMock);
    }

    /**
     * Tests the handling of the undo command.
     */
    @Test
    public void undo_nonEmptyStack_success() {
        // Mocking classes
        String previousCommand = "Last command";

        SaveState saveStateMock = mock(SaveState.class);
        ArrayList<Task> dummyTaskArray = new ArrayList<>();
        when(saveStateMock.getTasksFromSave()).thenReturn(dummyTaskArray);
        when(saveStateMock.getLastCommand()).thenReturn(previousCommand);

        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        when(saveStateStackMock.pop()).thenReturn(saveStateMock);

        TaskList taskListMock = mock(TaskList.class);

        // Re-mocking storage here as it is expected to perform actions
        Storage storageMock = mock(Storage.class);

        Ui uiMock = mock(Ui.class);
        when(uiMock.printUndoMessage(previousCommand)).thenReturn("Undone.");

        String command = "undo";

        // Testing
        assertEquals("Undone.", AdminCommandHandler.handleUndoCommand(command, taskListMock, uiMock,
                storageMock, saveStateStackMock));

        verify(saveStateStackMock, times(1)).pop();
        verify(taskListMock, times(1)).replaceTaskList(dummyTaskArray);
        verify(storageMock, times(1)).save(taskListMock);
        verify(uiMock, times(1)).printUndoMessage(previousCommand);
        verify(saveStateMock, times(1)).getTasksFromSave();
        verify(saveStateMock, times(1)).getLastCommand();

        verifyNoMoreInteractions(saveStateStackMock);
        verifyNoMoreInteractions(taskListMock);
        verifyNoMoreInteractions(storageMock);
        verifyNoMoreInteractions(uiMock);
        verifyNoMoreInteractions(saveStateMock);
    }

    /**
     * Tests the error handling of the undo command for empty stack.
     */
    @Test
    public void undo_emptyStack_exceptionThrown() {
        // Mocking classes
        SaveStateStack saveStateStackMock = mock(SaveStateStack.class);
        when(saveStateStackMock.pop()).thenReturn(null);

        String command = "undo";

        // Testing
        try {
            AdminCommandHandler.handleUndoCommand(command, dummyTaskList, dummyUi, dummyStorageMock,
                    saveStateStackMock);
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_NO_MORE_UNDOS, e.getMessage());
            verify(saveStateStackMock, times(1)).pop();
            verifyNoMoreInteractions(saveStateStackMock);
        }
    }
}
