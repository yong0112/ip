package noah.ui;

import java.util.List;

import noah.task.Task;
import noah.task.TaskList;

/**
 * Represents the UI for Noah chatbox.
 */
public class UI {

    private static final String DIVIDER = "____________________________________";

    private static String formatLine(String text) {
        return DIVIDER + "\n" + text + "\n" + DIVIDER;
    }
    public void printLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user.
     */
    public static String welcome() {
        return formatLine("Yo! Your boi Noah here.\nWhat can I do for you?");
    }

    /**
     * Returns string message with the corresponding task added.
     */
    public String printAddTask(Task task, int count) {

        return formatLine("Got it. I've added this task:"
                + "\n  " + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    /**
     * Returns a string message with the details of task removed.
     */
    public String printRemoveTask(Task removed, int count) {
        return formatLine("Yes sir. I've removed this task:"
                + "\n  " + removed
                + "\nNow you have " + count + " tasks in the list.");
    }

    /**
     * Returns a string message with the details of task marked.
     */
    public String printMarkTask(Task task) {
        return formatLine("Nice! I've marked this task as done:"
                + "\n  " + task);
    }

    /**
     * Returns a string message with the details of task unmarked.
     */
    public String printUnmarkTask(Task task) {
        return formatLine("OK, I've marked this task as not done yet:"
                + "\n  " + task);
    }

    /**
     * Returns a string message with the details of all the tasks.
     */
    public String printAllTasks(TaskList tasks) {
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return formatLine(msg);
    }

    /**
     * Returns a string message with the details of task found.
     */
    public String printMatchedTask(List<Task> matches) {
        if (matches.isEmpty()) {
            return formatLine("No matching tasks found.");
        } else {
            String msg = "Here are the matching tasks in your list:";
            for (int i = 0; i < matches.size(); i++) {
                msg += "\n" + (i + 1) + ". " + matches.get(i);
            }
            return formatLine(msg);
        }
    }

    /**
     * Returns a string message with the details of task edited.
     */
    public String printEditedTask(Task task) {
        return formatLine("Nice! I've updated this task:"
                + "\n  " + task);
    }

    public String printError(String message) {
        return formatLine(message);
    }

    public String goodBye() {
        return formatLine("Bye. See you next time my dawg!");
    }

}
