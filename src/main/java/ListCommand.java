public class ListCommand extends Command {

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
