public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws NoahException;

    public boolean isExit() {
        return false;
    }
}
