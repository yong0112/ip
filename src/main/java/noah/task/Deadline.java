package noah.task;

import java.time.LocalDateTime;

import noah.util.DateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a deadline task with The given deadline.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        String by = DateTime.dateToString(this.by);
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
