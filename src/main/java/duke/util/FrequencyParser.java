package duke.util;

import duke.exception.DuchessException;

import static duke.util.MagicStrings.ERROR_WRONG_FREQUENCY_FORMAT;

public class FrequencyParser {
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
        boolean isContainingBigWords = userInput.contains("biweekly") || userInput.contains("fortnightly");
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
