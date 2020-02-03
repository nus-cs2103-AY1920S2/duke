package duke.task;

import java.util.Arrays;
import java.util.stream.Collectors;

class EventTask extends Task {
    public EventTask(String[] inputArr) {
        this.type = "event";
        size++;
        this.description = Arrays.stream(inputArr)
                .map(str -> str.toLowerCase().equals("event") ? "" : str)
                .collect(Collectors.joining(" "));

    }

    @Override
    public String toString() {
        return " EVENT" + super.toString();
    }
}
