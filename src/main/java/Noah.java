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
            } else if (input.toLowerCase().startsWith("todo")) {
                String desc = input.substring(5).trim();
                tasks[count] = new Todo(desc);
                count++;
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[count - 1]);
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                printLine();
            } else if (input.toLowerCase().startsWith("deadline")) {
                String[] parts = input.substring(9).split(" /by");
                tasks[count] = new Deadline(parts[0].trim(), parts[1].trim());
                count++;
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[count - 1]);
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                printLine();
            } else if (input.toLowerCase().startsWith("event")) {
                String[] parts = input.substring(6).split(" /from | /to");
                tasks[count] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                count++;
                printLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[count - 1]);
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                printLine();
            } else {
                printLine();
                System.out.println("Invalid command! Sorry~~~");
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

    static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String to, String from) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

}