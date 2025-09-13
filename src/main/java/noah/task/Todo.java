package noah.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo task.
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
