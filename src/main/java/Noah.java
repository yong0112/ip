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
}