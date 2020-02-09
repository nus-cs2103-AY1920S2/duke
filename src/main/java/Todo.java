public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getShortName() {
        return "T";
    }

    /**
     * get full detail.
     * @param i index in array
     * @return
     */
    public String getFullDetail(Integer i) {
        String detail = String.format("%s[%s][%c] %s\n", i, this.getShortName(),
                (char) (Integer.parseInt(this.getStatusIcon(), 16)),
                this.description);
        return detail;
    }
}
