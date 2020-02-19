package duke.util;

import duke.exception.DuchessException;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;

import static duke.util.MagicStrings.ERROR_INVALID_SNOOZE_DURATION;
import static duke.util.MagicStrings.ERROR_INVALID_UNIT_OF_TIME;

public class DurationParser {
    public static TemporalAmount parseDuration(String userInput) throws DuchessException {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList(userInput.split("\\s", 2)));
        if (inputs.size() < 2) {
            throw new DuchessException(ERROR_INVALID_SNOOZE_DURATION);
        }
        int value = Integer.parseInt(inputs.get(0).trim());
        switch (inputs.get(1).trim().toLowerCase()) {
        case "hour":
            // Fallthrough
        case "hours":
            return Duration.ofHours(value);
        case "day":
            // Fallthrough
        case "days":
            return Period.ofDays(value);
        case "week":
            // Fallthrough
        case "weeks":
            return Period.ofWeeks(value);
        case "month":
            // Fallthrough
        case "months":
            return Period.ofMonths(value);
        case "year":
            // Fallthrough
        case "years":
            return Period.ofYears(value);
        default:
            throw new DuchessException(ERROR_INVALID_UNIT_OF_TIME);
        }
    }

    public static String parseDurationToString(String userInput) throws DuchessException {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList(userInput.split("\\s", 2)));
        if (inputs.size() < 2) {
            throw new DuchessException(ERROR_INVALID_SNOOZE_DURATION);
        }
        int value = Integer.parseInt(inputs.get(0).trim());
        switch (inputs.get(1).trim().toLowerCase()) {
        case "hour":
            // Fallthrough
        case "hours":
            return String.valueOf(value) + " hour" + (value > 1 ? "s" : "");
        case "day":
            // Fallthrough
        case "days":
            return String.valueOf(value) + " day" + (value > 1 ? "s" : "");
        case "week":
            // Fallthrough
        case "weeks":
            return String.valueOf(value) + " week" + (value > 1 ? "s" : "");
        case "month":
            // Fallthrough
        case "months":
            return String.valueOf(value) + " month" + (value > 1 ? "s" : "");
        case "year":
            // Fallthrough
        case "years":
            return String.valueOf(value) + " year" + (value > 1 ? "s" : "");
        default:
            throw new DuchessException(ERROR_INVALID_UNIT_OF_TIME);
        }
    }
}
