package noah.task;

import noah.exception.NoahException;
import noah.util.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime eventStartTime;
    protected LocalDateTime eventEndTime;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws NoahException {
        super(description);
        if (from.isAfter(to)) {
            throw new NoahException("Event start time must be before end time");
        }
        this.eventStartTime = from;
        this.eventEndTime = to;
    }

    @Override
    public String toString() {
        String from = DateTime.dateToString(this.eventStartTime);
        String to = DateTime.dateToString(this.eventEndTime);
        return "[E]" + super.toString() + " (eventStartTime: " + from + " eventEndTime: " + to + ")";
    }
}
