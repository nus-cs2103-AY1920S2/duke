import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void getStatusIconTest_defaultTask_notDoneMarkShown() {
        Task taskTest = new Task("Run");
        String actualResult = taskTest.getStatusIcon();
        String expectedResult = ":(";
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getStatusNumberTest_defaultTask_stringZeroReturned() {
        Task taskTest = new Task("Run");
        String actualResult = taskTest.getStatusNumber();
        String expectedResult = "0";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void markAsDoneTest_doneTask_doneMarkShown() {
        Task taskTest = new Task("Run");
        taskTest.markAsDone();
        String actualResult = taskTest.getStatusIcon();
        String expectedResult = ":)";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getStatusNumberTest_doneTask_stringOneReturned() {
        Task taskTest = new Task("Run");
        taskTest.markAsDone();
        String actualResult = taskTest.getStatusNumber();
        String expectedResult = "1";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDescriptionTest_runAsDescription_stringRunReturned() {
        Task taskTest = new Task("Run");
        String actualResult = taskTest.getDescription();
        String expectedResult = "Run";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringTest_correctInput_stringDescribingTaskObject() {
        Task taskTest = new Task("Run");
        String actualResult = taskTest.toString();
        String expectedResult = "[:(] Run";
        assertEquals(expectedResult, actualResult);
    }
}