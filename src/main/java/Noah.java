import java.util.Scanner;

public class Noah {
    public static void main(String[] args) {
        String[] list = new String[100];
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
            } else if (input.equals("list")) {
                printLine();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                printLine();
            } else {
                list[count] = input;
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

    public class Task {
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

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

}