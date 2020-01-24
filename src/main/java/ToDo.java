class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String status = super.getStatusIcon();
        return "[T]" + "[" + status + "] " + super.getDescription();
    }
}
