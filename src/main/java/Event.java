public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String to, String from) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
