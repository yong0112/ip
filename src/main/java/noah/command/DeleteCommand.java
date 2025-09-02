package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.storage.Storage;
import noah.exception.NoahException;
import noah.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        if (index < 0 || index >= tasks.size()) {
            throw new NoahException("Oops! I can't find the task");
        }

        try {
            storage.deleteTask(index);
        } catch (IOException e) {
            throw new NoahException(e.getMessage());
        }

        Task removed = tasks.deleteTask(index);
        ui.printRemoveTask(removed, tasks.size());
    }
}
