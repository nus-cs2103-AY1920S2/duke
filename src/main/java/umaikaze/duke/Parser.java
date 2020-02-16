/**
 * Paser class locates the description String and parse String into LocalDate and LocalTime
 * from String provided by TaskList class
 */

package umaikaze.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    String description;
    char separator;
    LocalDate date = null;
    LocalTime time = null;

    /**
     * This constructor is used by TaskList.getTask
     */
    public Parser(String[] words) {
        setDateTime(words);
        setDescriptionString(words);
    }

    private void setDateTime(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by") || words[i].equals("/at")) {
                System.out.println("time command recognized");
                if (words.length > i + 1 && (words[i + 1].contains("/") || words[i + 1].contains("-"))) {
                    separator = words[i + 1].contains("/") ? '/' : '-';
                    date = LocalDate.parse(words[i + 1], DateTimeFormatter.ofPattern("d" + separator + "M"
                            + separator + "yyyy"));
                }
                if (words.length > i + 2 && words[i + 2].contains(":")) {
                    time = LocalTime.parse(words[i + 2], DateTimeFormatter.ofPattern("H:mm"));
                }
            }
        }
    }

    private void setDescriptionString(String[] words) {
        StringBuilder description = new StringBuilder("");
        for (int i = 1; i < words.length; i++) {
            if (words[i].charAt(0) == '/') {
                if (i + 2 < words.length && words[i + 2].contains(":")) {
                    i += 2;
                } else {
                    i += 1;
                }
                continue;
            }
            description.append(words[i]).append(" ");
        }
        this.description = description.toString();
    }
}
