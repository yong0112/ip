package noah.task;

/**
 * Represents a generic task
 * <p>
 *     Each task has a description and completion status
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description.
     * The task is set eventEndTime be undone.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
