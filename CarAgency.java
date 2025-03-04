import utility.Management;
import utility.Talk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarAgency {
    public static void main(String[] args){
        System.out.println("             ASP Car Agency             ");
        System.out.println("------------------------------------");
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Hello! Welcome to The ASP Car Agency\n ");
            System.out.print("Are you an Admin or a Customer?\n");
            System.out.print("(0 -> Admin), (1 -> Customer), (2 -> Exit): ");
            try {
                while (true) {
                    int choice = input.nextInt();
                    if (choice >= 0 && choice <= 2){
                        if(choice == 0) {
                            Management management = new Management();
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                            management.start();
                            break;
                        } else if (choice == 1){
                            //use the Talk class
                            Talk talk = new Talk();
                            talk.greetings();
                            talk.howCanIHelp();
                            System.out.println("Thank you for Visiting The ASP Car Agency \nBye");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                            break;
                        }
                        else
                            System.out.println("Thank you for Visiting The ASP Car Agency \nBye");
                        System.exit(0);
                    }
                    else
                        System.out.print("Try again.\nEnter (0 -> Admin), (1 -> Customer), (2 -> Exit): ");
                }
            }catch (InputMismatchException ime){
                System.out.println("\nEnter an integer, try again:");
                System.out.println("------------------------------------");
                input.next();
            }
        }
    }
}
