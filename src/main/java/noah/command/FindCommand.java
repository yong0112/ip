package noah.command;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        List<Task> matches = tasks.findMatchingTasks(this.keyword);
        ui.printMatchedTask(matches);
    }
}
