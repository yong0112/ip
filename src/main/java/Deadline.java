import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String by = DateTime.dateToString(this.by);
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
