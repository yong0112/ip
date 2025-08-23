import java.util.Arrays;
import java.util.Scanner;

public class Noah {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int count = 0;
        Scanner sc = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Noah");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.toLowerCase().startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    tasks[index].markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    printLine();
                    System.out.println("Oops! I can't find the task");
                    printLine();
                }
            } else if (input.toLowerCase().startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    tasks[index].unmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    printLine();
                    System.out.println("Oops! I can't find the task");
                    printLine();
                }
            } else if (input.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
            } else {
                tasks[count] = new Task(input);
                count++;

                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }

        sc.close();
    }

    private static void printLine() {
        System.out.println("____________________________________");
    }

    static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

}