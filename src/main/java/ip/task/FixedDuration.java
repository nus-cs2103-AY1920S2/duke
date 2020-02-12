package ip.task;

public class FixedDuration extends Task {

    String duration;

    public FixedDuration(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (duration: " + duration + ")";
    }
}
