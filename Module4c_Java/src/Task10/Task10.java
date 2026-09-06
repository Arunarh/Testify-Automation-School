package Task10;

import java.util.Scanner;

public class Task10 {

    public static void verifyVisitor() {

        Scanner input = new Scanner(System.in);

        System.out.print("Are you coming for Testify Trainings? ");

        String answer = input.nextLine();

        if (answer.equalsIgnoreCase("Testify")) {
            System.out.println("Welcome to Testify Trainings!");
        } else {
            System.out.println("Sorry, you are not allowed.");
        }
    }

    public static void main(String[] args) {

        verifyVisitor();
    }
}