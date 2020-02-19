package duke.util;

import duke.exception.DuchessException;

import static duke.util.MagicStrings.ERROR_WRONG_FREQUENCY_FORMAT;

/**
 * The {@code FrequencyParser} is a helper class with static methods to parse
 * user provided inputs into {@code Frequency} objects.
 */
public class FrequencyParser {
    /**
     * Returns a {@code Frequency} object based on given {@code userInput} in
     * {@code String} format.
     *
     * <p>The formats accepted for the frequency include "daily", "day", "week",
     * "biweekly", "fortnightly", etc.
     *
     * @param userInput Given user input in {@code String} format.
     * @return A {@code Frequency} object.
     * @throws DuchessException If the given {@code userInput} is of the wrong format.
     */
    public static Frequency parseFrequency(String userInput) throws DuchessException {
        if (isContainingDailyKeywords(userInput)) {
            return Frequency.DAILY;
        }
        if (isContainingFortnightlyKeywords(userInput)) {
            return Frequency.FORTNIGHTLY;
        }
        if (isContainingWeeklyKeywords(userInput)) {
            return Frequency.WEEKLY;
        }
        if (isContainingMonthlyKeywords(userInput)) {
            return Frequency.MONTHLY;
        }
        throw new DuchessException(ERROR_WRONG_FREQUENCY_FORMAT);
    }

    private static boolean isContainingDailyKeywords(String userInput) {
        return userInput.contains("day") || userInput.contains("daily");
    }

    private static boolean isContainingFortnightlyKeywords(String userInput) {
        boolean isContainingBigWords = userInput.contains("biweek") || userInput.contains("fortnight");
        boolean isContainingSmallWords =
                userInput.contains("weekly") && (userInput.contains("two") || userInput.contains("2"));
        return isContainingBigWords || isContainingSmallWords;
    }

    private static boolean isContainingWeeklyKeywords(String userInput) {
        return userInput.contains("week");
    }

    private static boolean isContainingMonthlyKeywords(String userInput) {
        return userInput.contains("month");
    }
}
