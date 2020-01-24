package parser;

import exception.DukeException;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.time.LocalDate;

public class Parser {
  private static String[] taskTypes = {"todo", "event", "deadline"};

  public static Boolean isDoneDelete(String input) throws DukeException {
    if (Pattern.matches("(^(done|delete)\\s+.*|(.*\\s+(done|delete)\\s+.*)|.*\\s+(done|delete)$)",
        input)) {
      if (!Pattern.matches("^(done|delete)\\s+.*", input)) {
        throw new DukeException("Action should be at the front");
      }
      if (!Pattern.matches("^(done|delete)\\s+\\d+$", input)) {
        throw new DukeException("A task number must be provided");
      }
      return true;
    }
    return false;
  }

  public static String getType(String words) throws DukeException {
    String lowerCaseWords = words.toLowerCase();
    String acceptedTypes = String.format("(%s)", String.join("|", Parser.taskTypes));
    if (Pattern.matches(String.format("^%s\\s+.*|.*\\s+%s$|.*\\s+%s\\s+.*", acceptedTypes,
        acceptedTypes, acceptedTypes), lowerCaseWords)) {
      if (Pattern.matches(String.format("^%s\\s+.*", acceptedTypes), lowerCaseWords)) {
        return words.split(" ")[0].toLowerCase();
      } else {
        throw new DukeException("Please start with event type");
      }
    }
    throw new DukeException("No accepted types present");
  }

  public static String getContent(String description) throws DukeException {
    Matcher matcher = Pattern.compile("(/by|/at)").matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    if (index == -1 && description.trim().length() > 0) {
      return description.trim();
    }
    String content = description.substring(0, index).trim();
    if (content.length() > 0)
      return content;
    throw new DukeException("Content cannot be empty!");
  }

  public static String getDateTime(String description, String regex) throws DukeException {
    Matcher matcher = Pattern.compile(regex).matcher(description);
    int index = matcher.find() ? matcher.start() : -1;
    if (index == -1) {
      throw new DukeException(String.format("Please provide %s for this event type", regex));
    }
    String dateTime = description.substring(index + regex.length()).trim();

    if (dateTime.length() == 0)
      throw new DukeException("Please provide a time");

    return dateTime;
  }

  public static LocalTime getTime(String dateTime, String regex) throws DukeException {
    String time;
    try {
      String[] split = getDateTime(dateTime, regex).split(" ");
      time = String.join(" ", Arrays.copyOfRange(split, 1, split.length)).trim().toUpperCase();
    } catch (Exception e) {
      throw new DukeException(String.format(
          "Missing time/date information %n please provide date then time separated by a space"));
    }
    String[] timeRegex = {"H:m", "Hmm", "H.m", "h.m a", "h.m a", "h:m a", "h:m a", "hmm a", "hmma",
        "h a", "ha", "H"};

    for (String pattern : timeRegex) {
      try {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
      } catch (DateTimeParseException err) {
        continue;
      }
    }
    throw new DukeException("Time is in wrong format");
  }

  public static LocalDate getDate(String dateTime, String regex) throws DukeException {
    String date = getDateTime(dateTime, regex).split(" ")[0].trim();
    String[] dateRegex = {"ddMMyyyy", "yyyyMMdd", "d-M-yyyy", "d/M/yyyy", "yyyy-M-d", "yyyy/M/d"};
    for (String pattern : dateRegex) {
      try {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
      } catch (DateTimeParseException err) {
        continue;
      }
    }
    throw new DukeException("Date is in wrong format");
  }
}
