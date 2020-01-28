public class TaskListStub {

    public static Event createEvent(String input) throws GooseEmptyDescriptionException, GooseIllegalFormatException {
        String[] eventArr = input.split(" /at ");
        String[] descriptionSplit = eventArr[0].split(" ");
        String description = "";
        for (int i = 1; i < descriptionSplit.length; i++) {
            if (i == descriptionSplit.length - 1) {
                description += descriptionSplit[i];
            } else {
                description += descriptionSplit[i] + " ";
            }
        }

        if (description.isEmpty()) {
            throw new GooseEmptyDescriptionException("Honk! Description of an event cannot be empty.");
        } else {
            if (eventArr.length == 1) {
                throw new GooseIllegalFormatException("Honk! No event date specified.");
            }

            eventArr = eventArr[1].split(" ");
            String date = parseDate(eventArr[0]);
            String time = eventArr[1];
            return new Event(description, date, time);
        }
    }

    public static String parseDate(String date) {
        String[] dateArr = date.split("/");
        String day = dateArr[0];
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        String month = dateArr[1];
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String year = dateArr[2];

        return year + "-" + month + "-" + day;
    }
}
