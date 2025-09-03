package noah.command;

import noah.task.TaskList;
import noah.ui.UI;
import noah.util.Storage;

/**
 * Represents a command to list all tasks in the {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Executes this command by printing all tasks in the {@link TaskList}
     * to the user interface.
     *
     * @param tasks The task list containing all current tasks.
     * @param ui The user interface used to display the tasks.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        ui.printLine();
    }
}
