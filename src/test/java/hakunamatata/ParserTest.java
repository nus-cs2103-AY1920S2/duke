package hakunamatata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserTest_randomInput_throwHakunaMatataException() {
        HakunaMatataException actualException = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("test  ");
        });
        assertEquals("randomInput", actualException.getMessage());
    }

    @Test
    public void parserTest_missingDescriptionTest_throwHakunaMatataException() {
        HakunaMatataException actualException1 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("taskMissingDescription", actualException1.getMessage());

        HakunaMatataException actualException2 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("todo  ");
        });
        assertEquals("taskMissingDescription", actualException2.getMessage());

        HakunaMatataException actualException3 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("deadline");
        });
        assertEquals("taskMissingDescription", actualException3.getMessage());

        HakunaMatataException actualException4 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("deadline  ");
        });
        assertEquals("taskMissingDescription", actualException4.getMessage());

        HakunaMatataException actualException5 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("event");
        });
        assertEquals("taskMissingDescription", actualException5.getMessage());

        HakunaMatataException actualException6 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("event  ");
        });
        assertEquals("taskMissingDescription", actualException6.getMessage());
    }

    @Test
    public void parserTest_missingIndexTest_throwHakunaMatataException() {
        HakunaMatataException actualException1 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("done");
        });
        assertEquals("doneMissingIndex", actualException1.getMessage());

        HakunaMatataException actualException2 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("done  ");
        });
        assertEquals("doneMissingIndex", actualException2.getMessage());

        HakunaMatataException actualException3 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("delete");
        });
        assertEquals("deleteMissingIndex", actualException3.getMessage());

        HakunaMatataException actualException4 = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("delete  ");
        });
        assertEquals("deleteMissingIndex", actualException4.getMessage());
    }

    @Test
    public void parserTest_wrongIndexFormatTest_throwHakunaMatataException() {
        HakunaMatataException actualExceptionDone = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("done test");
        });
        assertEquals("doneWrongIndexFormat", actualExceptionDone.getMessage());

        HakunaMatataException actualExceptionDelete = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("delete 12test");
        });
        assertEquals("deleteWrongIndexFormat", actualExceptionDelete.getMessage());
    }

    @Test
    public void parserTest_wrongDescriptionFormat_throwException() {
        HakunaMatataException actualExceptionDeadlineMissingName =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("deadline /by 12:12 12/12/2020");
                });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionDeadlineMissingName.getMessage());

        HakunaMatataException actualExceptionDeadlineMissingBy =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("deadline test 12:12 12/12/2020");
                });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionDeadlineMissingBy.getMessage());

        HakunaMatataException actualExceptionDeadlineMissingDateTime =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("deadline test /by");
                });
        assertEquals("deadline&eventWrongDescriptionFormat",
                actualExceptionDeadlineMissingDateTime.getMessage());

        HakunaMatataException actualExceptionEventMissingName =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("event /at 2-4pm 12/12/2020");
                });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionEventMissingName.getMessage());

        HakunaMatataException actualExceptionEventMissingAt =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("event test 2-4pm 12/12/2020");
                });
        assertEquals("deadline&eventWrongDescriptionFormat", actualExceptionEventMissingAt.getMessage());

        HakunaMatataException actualExceptionEventMissingDateTime =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("event test /at");
                });
        assertEquals("deadline&eventWrongDescriptionFormat",
                actualExceptionEventMissingDateTime.getMessage());
    }

    @Test
    public void parserTest_wrongDateTimeFormat_throwException() {
        HakunaMatataException actualExceptionDeadlineWrongDateTimeFormat =
                Assertions.assertThrows(HakunaMatataException.class, () -> {
                    Parser.parse("deadline test /by invalidDateTime");
                });
        assertEquals("DateTimeParseError", actualExceptionDeadlineWrongDateTimeFormat.getMessage());
    }

    @Test
    public void parserTest_pastDateTime_throwHakunaMatataException() {
        HakunaMatataException actualException = Assertions.assertThrows(HakunaMatataException.class, () -> {
            Parser.parse("deadline test /by 12:12 12/12/2010");
        });
        assertEquals("pastDateTime", actualException.getMessage());
    }

}
