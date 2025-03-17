import utility.Management;
import utility.Talk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarAgency {
    public static void main(String[] args) {
        System.out.println("             ASP Car Agency             ");
        System.out.println("------------------------------------");

        Scanner input = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getValidInput(input);

            switch (choice) {
                case 0 -> handleAdmin();
                case 1 -> handleCustomer();
                case 2 -> exitProgram(input);
            }
        }
    }


    private static void displayMenu() {
        System.out.println("\nHello! Welcome to The ASP Car Agency\n");
        System.out.print("Are you an Admin or a Customer?\n");
        System.out.print("(0 -> Admin), (1 -> Customer), (2 -> Exit): ");
    }

    /**
     * Gets valid user input (0, 1, or 2) and handles invalid cases.
     */
    private static int getValidInput(Scanner input) {
        while (true) {
            try {
                int choice = input.nextInt();
                if (choice >= 0 && choice <= 2) {
                    return choice;
                }
                System.out.print("Invalid choice. Please enter (0 -> Admin), (1 -> Customer), (2 -> Exit): ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number (0, 1, or 2): ");
                input.next(); // Clear invalid input
            }
        }
    }


    private static void handleAdmin() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Management management = new Management();
        management.start();
    }


    private static void handleCustomer() {
        Talk talk = new Talk();
        talk.greetings();
        talk.howCanIHelp();
        System.out.println("Thank you for visiting The ASP Car Agency.\nBye!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }


    private static void exitProgram(Scanner input) {
        System.out.println("Thank you for visiting The ASP Car Agency.\nGoodbye!");
        input.close(); // Close the scanner before exiting
        System.exit(0);
    }
}
