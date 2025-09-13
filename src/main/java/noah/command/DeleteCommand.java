package noah.command;

import java.io.IOException;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Command that deletes tasks eventStartTime task list
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@link DeleteCommand} for the task at the given index.
     *
     * @param index The index of the task eventEndTime delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        try {
            storage.deleteTask(index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        Task removed = tasks.deleteTask(index);
        return ui.printRemoveTask(removed, tasks.size());
    }
}
