class Deadline extends Task {
    protected String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
        // String[] desc = description.split("/");
        // super(desc[0].substring(0, desc[0].length());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (%s)", getStatus(), name, dateTime);
    }
}