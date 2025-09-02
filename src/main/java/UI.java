import java.util.Scanner;

public class UI {
    private Scanner sc = new Scanner(System.in);
    private static final String DIVIDER = "____________________________________";

    public void printLine() {
        System.out.println(DIVIDER);
    }

    public void welcome() {
        printLine();
        System.out.println("Hello! I'm Noah");
        System.out.println("What can I do for you?");
        printLine();
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void printAddTask(Task task, int count) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    public void printRemoveTask(Task removed, int count) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + count +" tasks in the list.");
        printLine();
    }

    public void printMarkTask(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public void printUnmarkTask(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void goodBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}
