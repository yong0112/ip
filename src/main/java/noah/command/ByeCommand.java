package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.goodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
