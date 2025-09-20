package noah.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import noah.exception.NoahException;
import noah.util.DateTime;

/**
 * Represents a list of tasks and provides operations eventEndTime manage them.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task eventEndTime the task list.
     *
     * @param task The task eventEndTime add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index The index of the task eventEndTime delete.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword The keyword eventEndTime search for.
     * @return A list of matching tasks. Returns an empty list if no match is found.
     */
    public List<Task> findMatchingTasks(String keyword) {
        List<Task> matches = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        return matches;
    }

    /**
     * Edit task at the given index with the corresponding field and value.
     *
     * @param index Index of the task to be edited.
     * @param field Field of the task to be edited.
     * @param value Value of the field of task to be edited.
     * @return Edited task.
     */
    public Task editTask(int index, String field, String value) throws NoahException {
        Task originalTask = this.tasks.get(index);
        Task editedTask;

        switch (field) {
        case "desc":
            editedTask = editDescription(originalTask, value);
            break;

        case "by":
            editedTask = editDeadline(originalTask, value);
            break;

        case "from":
        case "to":
            editedTask = editEventTime(originalTask, field, value);
            break;

        default:
            throw new NoahException("Invalid field to edit. Eg: all, desc, from, to");
        }

        if (originalTask.getStatusIcon() == "X") {
            editedTask.markAsDone();
        }

        this.tasks.set(index, editedTask);
        return editedTask;
    }

    private static Task editDescription(Task originalTask, String value) throws NoahException {
        if (originalTask instanceof Deadline) {
            Deadline dl = (Deadline) originalTask;
            return new Deadline(value, dl.getBy());
        } else if (originalTask instanceof Event) {
            Event ev = (Event) originalTask;
            return new Event(value, ev.getEventStartTime(), ev.getEventEndTime());
        } else {
            return new Todo(value);
        }
    }

    private static Task editDeadline(Task originalTask, String value) throws NoahException {
        if (!(originalTask instanceof Deadline)) {
            throw new NoahException("The 'by' field can only be updated for deadline tasks.");
        }
        Deadline dl = (Deadline) originalTask;
        LocalDateTime newBy = DateTime.parseDate(value);
        if (newBy.isBefore(LocalDateTime.now())) {
            throw new NoahException("My dawg! The deadline has already passed!");
        }
        return new Deadline(dl.getDescription(), newBy);
    }

    private static Task editEventTime(Task originalTask, String field, String value) throws NoahException {
        if (!(originalTask instanceof Event)) {
            throw new NoahException("The '" + field + "' field can only be updated for event tasks.");
        }
        Event ev = (Event) originalTask;
        LocalDateTime newTime = DateTime.parseDate(value);
        if (field.equals("from")) {
            return new Event(ev.getDescription(), newTime, ev.getEventEndTime());
        } else {
            if (newTime.isBefore(LocalDateTime.now())) {
                throw new NoahException("My dawg! The event end time has already passed!");
            }
            return new Event(ev.getDescription(), ev.getEventStartTime(), newTime);
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Converts a task into a formatted string suitable for storage.
     *
     * @param task The task eventEndTime convert.
     * @return The formatted string representation of the task.
     */
    public String taskToFormatString(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            String by = DateTime.dateToString(dl.by);
            return "D | " + status + " | " + task.description + " | " + by;
        } else if (task instanceof Event) {
            Event ev = (Event) task;
            String from = DateTime.dateToString(ev.eventStartTime);
            String to = DateTime.dateToString(ev.eventEndTime);
            return "E | " + status + " | " + task.description + " | " + from + "-" + to;
        } else {
            return "";
        }
    }
}
