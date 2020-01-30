package bot.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.util.Optional;

/**
 * Custom class that represents time of a Deadline
 * Task or Event Task
 */
public class PrettyTime implements Comparable<PrettyTime> {
    private final Optional<String> rawText;
    private final Optional<Integer> time;
    private final Optional<LocalDate> date;

    /**
     * Constructor for a PrettyTime, using a
     * String with date in this format:
     * DD-MM-YYYY-tttt, e.g. 5-12-2020-1300 to refer
     * to 5th December 2020, 1 pm
     *
     * @param s The String in DD-MM-YYYY format, or
     *          DD-MM-YYYY-tttt format
     */
    public PrettyTime(String s) {
        String[] split = s.split("-");
        boolean splitSuccessful = true;
        String date = "";
        String month = "";
        String year = "";
        try {
            date = split[0].length() == 2 ? split[0] : "0" + split[0];
            month = split[1].length() == 2 ? split[1] : "0" + split[1];
            year = split[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            splitSuccessful = false;
        }

        LocalDate parsedDate = null;
        boolean parseSuccessful = true;
        if (splitSuccessful) {
            try {
                parsedDate = LocalDate.parse(year + "-" + month + "-" + date);
            } catch (DateTimeParseException e) {
                parseSuccessful = false;
            }
        }



        if (splitSuccessful && parseSuccessful) {
            this.date = Optional.of(parsedDate);
            this.rawText = Optional.empty();

            int parsedTime;
            boolean timeSuccessful = true;

            try {
                parsedTime = Integer.parseInt(split[3]);
                if (split[3].length() != 4) {
                    timeSuccessful = false;
                } else {
                    int minutes = Integer.parseInt(split[3].substring(2));
                    if (minutes < 0 || minutes > 60) {
                        timeSuccessful = false;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                parsedTime = -1;
            }

            if (parsedTime < 0 || parsedTime > 2359) {
                timeSuccessful = false;
            }

            if (timeSuccessful) {
                this.time = Optional.of(parsedTime);
            } else {
                this.time = Optional.empty();
            }
        } else {
            this.rawText = Optional.of(s);
            this.date = Optional.empty();
            this.time = Optional.empty();
        }
    }

    /**
     * Checks whether this PrettyTime has a LocalDate
     * representation.
     *
     * @return True, if there exists the LocalDate
     * representation.
     */
    public boolean hasLocalDate() {
        return this.date.isPresent();
    }

    /**
     * Checks whether this PrettyTime actually has
     * a value for time, apart from the date. The time
     * can only exist if the LocalDate exists
     *
     * @return True, if there exists a time (which
     * would also imply that the date exists)
     */
    public boolean hasTime() {
        return this.time.isPresent();
    }

    /**
     * Generates a String representing this
     * PrettyTime in the same format in which
     * it was entered (DD-MM-YYYY-tttt, if
     * the LocalDate representation exists)
     *
     * @return String representing date and time
     * in DD-MM-YYYY-tttt format, if possible
     */
    public String toRaw() {
        return this.date.map(
                date -> date.format(DateTimeFormatter.ofPattern("dd-MM-y"))
                        + this.time.map(t -> "-" + String.format("%04d", t))
                        .orElse("")
        ).orElseGet(
                () -> this.rawText.orElse("")
        );
    }

    @Override
    public int compareTo(PrettyTime pt) {
        if (this.hasLocalDate() && pt.hasLocalDate()) {
            // if date is the same, check the time
            if (this.date.get().equals(pt.date.get())) {
                return this.time.get().compareTo(pt.time.get());
            } else {
                return this.date.get().compareTo(pt.date.get());
            }
        } else if (!this.hasLocalDate() && !pt.hasLocalDate()) {
            return this.rawText.get().compareTo(pt.rawText.get());
        } else if (this.hasLocalDate() && !pt.hasLocalDate()) {
            return -1;
        } else {
            // !this.hasLocalDate() && pt.hasLocalDate()
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PrettyTime) {
            PrettyTime pt = (PrettyTime) o;
            if (this.hasLocalDate() && pt.hasLocalDate()) {
                if (this.hasTime() && pt.hasTime()) {
                    return this.date.get().equals(pt.date.get())
                            && this.time.get().equals(pt.time.get());
                } else if (!this.hasLocalDate() && !pt.hasLocalDate()) {
                    return this.date.get().equals(pt.date.get());
                } else {
                    return false;
                }
            } else if (!this.hasLocalDate() && !pt.hasLocalDate()) {
                return this.rawText.get().equals(pt.rawText.get());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks whether this PrettyTime has the same
     * date as another PrettyTime. Time is ignored
     *
     * @param pt The PrettyTime to match to
     * @return True, if both PrettyTimes have the
     * same date
     */
    public boolean matchDate(PrettyTime pt) {
        if (this.hasLocalDate() && pt.hasLocalDate()) {
            return this.date.get().equals(pt.date.get());
        } else if (!this.hasLocalDate() && !pt.hasLocalDate()) {
            return this.rawText.get().equals(pt.rawText.get());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.date.map(
                date -> date.format(DateTimeFormatter.ofPattern("d MMMM y"))
                        + this.time.map(t -> ", at " + String.format("%04d", t))
                        .orElse("")
        ).orElseGet(
                () -> this.rawText.orElse("")
        );
    }
}
