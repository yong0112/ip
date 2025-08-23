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
            try {
                String[] parts = input.split(" ", 2);
                Command command = Command.from(parts[0]);

                switch (command) {
                    case BYE:
                        printLine();
                        System.out.println("Bye. Hope to see you again soon!");
                        printLine();
                        return;

                    case MARK:
                        if (parts.length < 2) {
                            throw new NoahException("Please specify a task number to mark.");
                        }
                        int markIndex = Integer.parseInt(parts[1]) - 1;
                        if (markIndex >= 0 && markIndex < count) {
                            tasks[markIndex].markAsDone();
                            printLine();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks[markIndex]);
                            printLine();
                        } else {
                            throw new NoahException("Oops! I can't find the task");
                        }
                        break;

                    case UNMARK:
                        if (parts.length < 2) {
                            throw new NoahException("Please specify a task number to unmark.");
                        }
                        int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                        if (unmarkIndex >= 0 && unmarkIndex < count) {
                            tasks[unmarkIndex].unmark();
                            printLine();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks[unmarkIndex]);
                            printLine();
                        } else {
                            throw new NoahException("Oops! I can't find the task");
                        }
                        break;

                    case LIST:
                        if (parts.length < 2) {
                            printLine();
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < count; i++) {
                                System.out.println((i + 1) + ". " + tasks[i]);
                            }
                            printLine();
                        } else {
                            throw new NoahException("Invalid description! Try list");
                        }
                        break;

                    case TODO:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new NoahException("The description of a todo cannot be empty.");
                        }
                        tasks[count] = new Todo(parts[1].trim());
                        count++;
                        printAddTask(tasks[count - 1], count);
                        break;

                    case DEADLINE:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new NoahException("The description of a deadline cannot be empty.");
                        }
                        if (!parts[1].contains("/by")) {
                            throw new NoahException("The deadline format must include /by");
                        }
                        String[] dl = parts[1].split(" /by", 2);
                        tasks[count] = new Deadline(dl[0].trim(), dl[1].trim());
                        count++;
                        printAddTask(tasks[count - 1], count);
                        break;

                    case EVENT:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new NoahException("The description of a event cannot be empty.");
                        }
                        if (!parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new NoahException("The event format must include /from and /to");
                        }
                        String[] ev = parts[1].split(" /from | /to");
                        tasks[count] = new Event(ev[0].trim(), ev[1].trim(), ev[2].trim());
                        count++;
                        printAddTask(tasks[count - 1], count);
                        break;

                    case UNKNOWN:
                        throw new NoahException("Sorry~ I don't know what that means.");

                    default:
                        throw new NoahException("Unexpected error.");
                }
            } catch (NoahException e) {
                printError(e.getMessage());
            } catch (NumberFormatException e) {
                printError("OOPS! Task number must be an integer.");
            }
        }
    }

    private static void printLine() {
        System.out.println("____________________________________");
    }

    private static void printAddTask(Task task, int count) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    private static void printError(String message) {
        printLine();
        System.out.println(message);
        printLine();
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

    static class NoahException extends Exception {
        public NoahException(String message) {
            super(message);
        }
    }

    enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, UNKNOWN;

        static Command from(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

}