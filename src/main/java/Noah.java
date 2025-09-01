import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Noah {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Storage database = new Storage();

        //Load tasks from file
        try {
            tasks = database.loadTasks();
        } catch (NoahException e) {
            printError(e.getMessage());
        }

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
                        if (markIndex >= 0 && markIndex < tasks.size()) {
                            Task task = tasks.get(markIndex);
                            task.markAsDone();
                            try {
                                database.updateTask(taskToFormatString(task), markIndex);
                            } catch (IOException e) {
                                printError(e.getMessage());
                                return;
                            }
                            printLine();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks.get(markIndex));
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
                        if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                            Task task = tasks.get(unmarkIndex);
                            task.unmark();
                            try {
                                database.updateTask(taskToFormatString(task), unmarkIndex);
                            } catch (IOException e) {
                                printError(e.getMessage());
                                return;
                            }
                            printLine();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks.get(unmarkIndex));
                            printLine();
                        } else {
                            throw new NoahException("Oops! I can't find the task");
                        }
                        break;

                    case LIST:
                        if (parts.length < 2) {
                            printLine();
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
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
                        Task newTodo = new Todo(parts[1].trim());
                        try {
                            database.addTask(taskToFormatString(newTodo));
                        } catch (IOException e) {
                            printError(e.getMessage());
                        }
                        tasks.add(newTodo);

                        printAddTask(tasks.get(tasks.size() - 1), tasks.size());
                        break;

                    case DEADLINE:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new NoahException("The description of a deadline cannot be empty.");
                        }
                        if (!parts[1].contains("/by")) {
                            throw new NoahException("The deadline format must include /by");
                        }
                        String[] dl = parts[1].split(" /by", 2);
                        if (dl.length < 2 || dl[0].trim().isEmpty()) {
                            throw new NoahException("The description of a deadline cannot be empty.");
                        }
                        LocalDateTime deadline = DateTime.parseDate(dl[1].trim());
                        Task newDl = new Deadline(dl[0].trim(), deadline);
                        try {
                            database.addTask(taskToFormatString(newDl));
                        } catch (IOException e) {
                            printError(e.getMessage());
                        }
                        tasks.add(newDl);
                        printAddTask(tasks.get(tasks.size() - 1), tasks.size());
                        break;

                    case EVENT:
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new NoahException("The description of a event cannot be empty.");
                        }
                        if (!parts[1].contains("/from") || !parts[1].contains("/to")) {
                            throw new NoahException("The event format must include /from and /to");
                        }
                        String[] ev = parts[1].split(" /from | /to");
                        if (ev.length < 2 || ev[0].trim().isEmpty()) {
                            throw new NoahException("The description of a event cannot be empty.");
                        }
                        LocalDateTime from = DateTime.parseDate(ev[1].trim());
                        LocalDateTime to = DateTime.parseDate(ev[2].trim());
                        Task newEv = new Event(ev[0].trim(), from, to);
                        try {
                            database.addTask(taskToFormatString(newEv));
                        } catch (IOException e) {
                            printError(e.getMessage());
                        }
                        tasks.add(newEv);
                        printAddTask(tasks.get(tasks.size() - 1), tasks.size());
                        break;

                    case UNKNOWN:
                        throw new NoahException("Sorry~ I don't know what that means.");

                    case DELETE:
                        if (parts.length < 2) {
                            throw new NoahException("Please specify a task number to delete.");
                        }
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                            try {
                                database.deleteTask(deleteIndex);
                            } catch (IOException e) {
                                printError(e.getMessage());
                            }
                            Task removed = tasks.remove(deleteIndex);
                            printLine();
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + removed);
                            System.out.println("Now you have " + tasks.size() +" tasks in the list.");
                            printLine();
                        } else {
                            throw new NoahException("Oops! I can't find the task");
                        }
                        break;

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

    private static String taskToFormatString (Task task) {
        String status = task.isDone ? "1": "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            String by = DateTime.dateToString(dl.by);
            return "D | " + status + " | " + task.description + " | " + by;
        } else if (task instanceof Event) {
            Event ev = (Event) task;
            String from = DateTime.dateToString(ev.from);
            String to = DateTime.dateToString(ev.to);
            return "E | " + status + " | " + task.description + " | " + from + "-" + to;
        } else {
            return "";
        }
    }

    enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, DELETE, UNKNOWN;

        static Command from(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
}