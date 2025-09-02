package noah.task;

import noah.util.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String from = DateTime.dateToString(this.from);
        String to = DateTime.dateToString(this.to);
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
