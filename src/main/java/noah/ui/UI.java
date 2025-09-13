package noah.ui;

import noah.task.Task;
import noah.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class UI {

    private static final String DIVIDER = "____________________________________";

    private static String formatLine(String text) {
        return DIVIDER + "\n" + text + "\n" + DIVIDER;
    }
    public void printLine() {
        System.out.println(DIVIDER);
    }

    public static String welcome() {
        return formatLine("Hello! I'm Noah\nWhat can I do for you?");
    }

    public String printAddTask(Task task, int count) {

        return formatLine("Got it. I've added this task:" +
                "\n  " + task +
                "\nNow you have " + count + " tasks in the list.");
    }

    public String printRemoveTask(Task removed, int count) {
        return formatLine("Noted. I've removed this task:" +
                "\n  " + removed +
                "\nNow you have " + count +" tasks in the list.");
    }

    public String printMarkTask(Task task) {
        return formatLine("Nice! I've marked this task as done:" +
                "\n  " + task);
    }

    public String printUnmarkTask(Task task) {
        return formatLine("OK, I've marked this task as not done yet:" +
                "\n  " + task);
    }

    public String printAllTasks(TaskList tasks) {
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return formatLine(msg);
    }

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

    public String printError(String message) {
        return formatLine(message);
    }

    public String goodBye() {
        return formatLine("Bye. Hope eventEndTime see you again soon!");
    }

}
