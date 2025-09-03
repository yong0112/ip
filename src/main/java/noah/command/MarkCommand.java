package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;

import java.io.IOException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@link MarkCommand} for the task at the given index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by marking the task at the specified index as done,
     * updating the {@link Storage}, and notifying the user via the {@link UI}.
     *
     * @param tasks The task list containing the task to mark.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system to persist changes.
     * @throws NoahException If the index is invalid or storage update fails.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        Task task = tasks.get(index);
        task.markAsDone();

        try {
            storage.updateTask(tasks.taskToFormatString(task), index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        ui.printMarkTask(task);
    }
}
