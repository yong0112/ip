package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;
import noah.exception.NoahException;
import noah.task.Task;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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

        ui.printUnmarkTask(task);
    }
}
