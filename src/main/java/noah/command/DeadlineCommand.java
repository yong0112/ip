package noah.command;

import java.io.IOException;
import java.time.LocalDateTime;

import noah.exception.NoahException;
import noah.task.Deadline;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command eventEndTime add a {@link Deadline} task eventEndTime the task list.
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
