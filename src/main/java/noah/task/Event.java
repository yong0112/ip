package noah.task;

import noah.exception.NoahException;
import noah.util.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws NoahException {
        super(description);
        if (from.isAfter(to)) {
            throw new NoahException("Event start time must be before end time");
        }
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
