public class Event extends Task {

    /** as good practice every class should have it's own private serialVersionUID */
    private static final long serialVersionUID = 7761205144753475365L;
    protected String at;

    public Event(String description, String at) {
        super(description.strip());
        this.at = at.strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}