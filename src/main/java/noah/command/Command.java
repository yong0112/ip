package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.storage.Storage;
import noah.exception.NoahException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws NoahException;

    public boolean isExit() {
        return false;
    }
}
