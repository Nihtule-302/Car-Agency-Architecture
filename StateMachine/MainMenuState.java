package StateMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuState implements State {
    CarAgencyStateMachine machine;
    Scanner input;


    @Override
    public void enter(CarAgencyStateMachine machine) {
        this.machine = machine;
        input = new Scanner(System.in);

        System.out.println("             ASP Car Agency             ");
        System.out.println("------------------------------------");
    }

    @Override
    public void Do() {
        mainMenuLogic();
    }

    @Override
    public void exit() {}



    private void mainMenuLogic() {
        while (true) {
            displayMenu();
            int choice = getValidInput(input);

            switch (choice) {
                case 0 -> handleAdmin(machine);
                case 1 -> handleCustomer(machine);
                case 2 -> exitProgram(machine);
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nHello! Welcome to The ASP Car Agency\n");
        System.out.print("Are you an Admin or a Customer?\n");
        System.out.print("(0 -> Admin), (1 -> Customer), (2 -> Exit): ");
    }

    /**
     * Gets valid user input (0, 1, or 2) and handles invalid cases.
     */
    private int getValidInput(Scanner input) {
        while (true) {
            try {
                int choice = input.nextInt();
                if (choice >= 0 && choice <= 2) {
                    return choice;
                }
                System.out.print(
                        "Invalid choice. Please enter (0 -> Admin), (1 -> Customer), (2 -> Exit): ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number (0, 1, or 2): ");
                input.next(); // Clear invalid input
            }
        }
    }


    private void handleAdmin(CarAgencyStateMachine machine) {
        machine.SwitchState(new AdminPerspectiveState());
    }

    private void handleCustomer(CarAgencyStateMachine machine) {
        machine.SwitchState(new CustomerPerspectiveState());
    }

    private void exitProgram(CarAgencyStateMachine machine) {
        machine.SwitchState(new ExitState());
    }
}
