public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private String shortForm;

    TaskType(String shortForm) {
        this.shortForm = shortForm;
    }

    public String toString() {
        return this.shortForm;
    }

}