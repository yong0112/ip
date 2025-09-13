package noah.command;

import java.util.List;

import noah.exception.NoahException;
import noah.task.Task;
import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws NoahException {
        List<Task> matches = tasks.findMatchingTasks(this.keyword);
        return ui.printMatchedTask(matches);
    }
}
