package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    private String command;

    Parser(String next) {
        this.command = next;
    }
    public String getTaskType() {
        if (this.command.trim().contains(" ")) {
            return this.command.trim().split(" ")[0];
        } else {
            return this.command.trim();
        }
    }

    public String getSecond() {
        return this.command.trim().split(" ")[1];
    }

    public String getThird() {
        if (this.command.contains("/")) {
            return this.command.substring(this.command.indexOf(" ") + 1, this.command.indexOf("/") - 1);
        }
        else {
            return this.command.trim().substring(5);
        }
    }

    public LocalDateTime getDate() {
        return extractDate(this.command.substring(this.command.indexOf("/") + 4));
    }

    static LocalDateTime extractDate(String next) {
        StringBuilder forTime = new StringBuilder(next.substring(next.indexOf(" ") + 1));
        forTime.insert(2, ':');
        String time = forTime.toString();
        String[] reverse = next.substring(0, next.indexOf(" "))
                .split("/");
        String[] reversed = reverse;
        String temp = String.format("%2s",reverse[0]).replace(" ", "0");
        reversed[0] = String.format("%4s",reverse[2]).replace(" ", "0");
        reversed[2] = temp;
        reversed[1] = String.format("%2s", reverse[1]).replace(" ", "0");
        String date = String.join("-", reversed);
        LocalDateTime taskDate = LocalDate.parse(date)
                .atTime(LocalTime.parse(time))
                ;
        return taskDate;
    }

}
