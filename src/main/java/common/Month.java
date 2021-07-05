package common;

public enum Month {
    Jan (1) ,
    Feb (2),
    Mar (3),
    Apr (4),
    May (5),
    Jun (6),
    Jul (7),
    Aug (8),
    Sep (9),
    Oct (10),
    Nov (11),
    Dec (12);

    private final int value;

    Month(int i) {
        this.value = i;
    }
    public static String valueOf(int month) {
        switch (month) {
        case 1:
            return "Jan";
        case 2:
            return "Feb";
        case 3:
            return "Mar";
        case 4:
            return "Apr";
        case 5:
            return "May";
        case 6:
            return "Jun";
        case 7:
            return "Jul";
        case 8:
            return "Aug";
        case 9:
            return "Sep";
        case 10:
            return "Oct";
        case 11:
            return "Nov";
        case 12:
            return "Dec";
        default:
            return null;
        }
    }


    public int getValue() {
        return value;
    }
}
