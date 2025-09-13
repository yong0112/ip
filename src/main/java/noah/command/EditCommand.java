package noah.command;

import java.io.IOException;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Command that edit the relevant task details.
 */
public class EditCommand extends Command {
    private int index;
    private String field;
    private String value;

    /**
     * Constructs a {@link EditCommand} for the task at the given index and
     * modifies with the corresponding field and value.
     */
    public EditCommand(int index, String field, String value) {
        this.index = index;
        this.field = field;
        this.value = value;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        assert index >= 0 && index < tasks.size() : "Invalid index to delete";

        Task editedTask = tasks.editTask(index, field, value);
        try {
            storage.updateTask(tasks.taskToFormatString(editedTask), index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }
        return ui.printEditedTask(editedTask);
    }
}
