package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Event;

import java.io.IOException;

import java.time.LocalDateTime;

/**
 * Represents a command to add an {@link Event} task to the task list.
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

    /**
     * Executes this command by creating a new {@link Event} task, adding it to the {@link TaskList},
     * updating the {@link Storage}, and notifying the user via the {@link UI}.
     *
     * @param tasks The task list to which the new event will be added.
     * @param ui The user interface used to display messages.
     * @param storage The storage system used to persist the task.
     * @throws NoahException If there is an error writing to storage.
     */
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
