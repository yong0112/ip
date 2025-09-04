package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;
import noah.task.Deadline;

import java.io.IOException;

import java.time.LocalDateTime;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Constructs a {@link DeadlineCommand} with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the task should be completed.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command by creating a new {@link Deadline} task, adding it to the {@link TaskList},
     * updating the {@link Storage}, and notifying the user via the {@link UI}.
     *
     * @param tasks The task list to which the new deadline task will be added.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system used to save the task persistently.
     * @throws NoahException If there is an error writing to storage.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        Task newDl = new Deadline(this.description, this.by);
        try {
            storage.addTask(tasks.taskToFormatString(newDl));
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }
        tasks.addTask(newDl);
        return ui.printAddTask(newDl, tasks.size());
    }
}
