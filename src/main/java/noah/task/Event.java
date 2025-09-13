package noah.task;

import java.time.LocalDateTime;

import noah.exception.NoahException;
import noah.util.DateTime;

/**
 * Represents an event with start time and end time
 */
public class Event extends Task {
    protected LocalDateTime eventStartTime;
    protected LocalDateTime eventEndTime;

    /**
     * Constructs an event task with the given start and end time.
     * @param description Description of task.
     * @param eventStartTime Start time of task.
     * @param eventEndTime End time of task.
     * @throws NoahException Throws exception if start time is after end time.
     */
    public Event(String description, LocalDateTime eventStartTime, LocalDateTime eventEndTime) throws NoahException {
        super(description);
        if (eventStartTime.isAfter(eventEndTime)) {
            throw new NoahException("Event start time must be before end time");
        }
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public LocalDateTime getEventStartTime() {
        return this.eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return this.eventEndTime;
    }

    @Override
    public String toString() {
        String from = DateTime.dateToString(this.eventStartTime);
        String to = DateTime.dateToString(this.eventEndTime);
        return "[E]" + super.toString() + " (eventStartTime: " + from + " eventEndTime: " + to + ")";
    }
}
