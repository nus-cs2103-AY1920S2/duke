package jiachen.duke;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DateTimeUtilTest {

    @Test
    void isNatualDate() {
        assertTrue(DateTimeUtil.getInstance().isNatualDate("monday"));
        assertTrue(DateTimeUtil.getInstance().isNatualDate("tue"));
        assertFalse(DateTimeUtil.getInstance().isNatualDate("blahh"));
    }

    @Test
    void convertFromNatualDate() {
        String datetime = DateTimeUtil.getInstance().convertFromNatualDate("mon");
        LocalDateTime ldt = LocalDateTime.parse(datetime, DateTimeUtil.inputFormatter);
        assertEquals(ldt.getDayOfWeek(), DayOfWeek.MONDAY);
    }
}