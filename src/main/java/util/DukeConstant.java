package util;

public class DukeConstant {
    public static final String DELIMITER_WHITESPACE = " ";
    public static final String DELIMITER_COMMA = ",";
    public static final String DELIMITER_DATE = "/";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITER_AT = "/at";

    //
    public static final String TICK_ICON = "\u2714";
    public static final String CROSS_ICON = "\u274C";

    // Used in util.task.DeadlineTask#createDeadlineTask and util.task.EventTask#createEventTask
    public static final int TASK_HOUR_START_INDEX = 0;
    public static final int TASK_HOUR_END_INDEX = 2;

    public static final int DATE_DAY_INDEX = 0;
    public static final int DATE_MONTH_INDEX = 1;
    public static final int DATE_YEAR_INDEX = 2;

    public static final int DAY_ONE = 1;
    public static final int DAY_TWO = 2;
    public static final int DAY_THREE = 3;
    public static final String DAY_FIRST = "st";
    public static final String DAY_SECOND = "nd";
    public static final String DAY_THIRD = "rd";
    public static final String DAY_NTH = "th";

    // Used in Parser#parse
    public static final String LIST_COMMAND = "list";
    public static final String SAVE_COMMAND = "save";
    public static final String CLEAR_COMMAND = "clear";
    public static final String BYE_COMMAND = "bye";
    public static final String FIND_COMMAND = "find";
    public static final String SCHEDULE_COMMAND = "schedule";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String HELP_COMMAND = "help";

    // Used in Storage#stringToDate
    public static final String JAN_STRING = "Jan";
    public static final String JAN_NUM = "01";
    public static final String FEB_STRING = "Feb";
    public static final String FEB_NUM = "02";
    public static final String MAR_STRING = "Mar";
    public static final String MAR_NUM = "03";
    public static final String APR_STRING = "Apr";
    public static final String APR_NUM = "04";
    public static final String MAY_STRING = "May";
    public static final String MAY_NUM = "05";
    public static final String JUN_STRING = "Jun";
    public static final String JUN_NUM = "06";
    public static final String JUL_STRING = "Jul";
    public static final String JUL_NUM = "07";
    public static final String AUG_STRING = "Aug";
    public static final String AUG_NUM = "08";
    public static final String SEP_STRING = "Sep";
    public static final String SEP_NUM = "09";
    public static final String OCT_STRING = "Oct";
    public static final String OCT_NUM = "10";
    public static final String NOV_STRING = "Nov";
    public static final String NOV_NUM = "11";
    public static final String DEC_STRING = "Dec";
    public static final String DEC_NUM = "12";
}
