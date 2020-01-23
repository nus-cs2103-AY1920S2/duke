package duke;

import java.lang.StringBuilder;

public class Deadline extends Task {
    String timing;
    String description;

    Deadline(String input) {
        super(input);
        this.timing = getTiming(input);
        this.description = getDescription(input);
    }

    private String getTiming(String input) {
        String[] strArr = input.split(" ");
        int index = 0;
        for (int j = 0; j < strArr.length; j++) {
            String stringItem = strArr[j];
            if (stringItem.equals("/by")) {
                index = j;
                break;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = index+1; i <strArr.length ; i++ ) {
            str.append(" ").append(strArr[i]);
        }
        return str.toString();
    }

    //updates the description given the "/by" index
    private String getDescription(String input) {
        StringBuilder str = new StringBuilder();
        String[] strArr = input.split(" ");
        for (int i = 1; i < strArr.length; i++ ) {
            if (strArr[i].equals("/by")) {
                break;
            }
            str.append(strArr[i]).append(" ");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[D]");
        str.append(this.getStatusIcon())
                .append(" ")
                .append(description)
                .append("(by:")
                .append(timing)
                .append(")");
        return str.toString();
    }
}
