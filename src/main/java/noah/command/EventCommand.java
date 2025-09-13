package noah.command;

import java.io.IOException;
import java.time.LocalDateTime;

import noah.exception.NoahException;
import noah.task.Event;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime add an {@link Event} task eventEndTime the task list.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@link EventCommand} with the specified description, start, and end times.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newEv = new Event(this.description, this.from, this.to);

        try {
            storage.addTask(tasks.taskToFormatString(newEv));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        tasks.addTask(newEv);
        return ui.printAddTask(newEv, tasks.size());
    }
}
