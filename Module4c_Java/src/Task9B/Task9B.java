package Task9B;

import java.util.Scanner;

public class Task9B {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the word: ");
        String word = input.nextLine();

        while (!word.equals("testify")) {
            System.out.println("try again");

            System.out.print("Enter the word: ");
            word = input.nextLine();
        }

        System.out.println("Correct! You entered testify.");

        input.close();
    }
}