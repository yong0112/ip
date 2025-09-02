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
