import java.util.Scanner;

public class Noah {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Noah");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            printLine();
            System.out.println(" " + input);
            printLine();
        }

        sc.close();
    }

    private static void printLine() {
        System.out.println("____________________________________");
    }
}