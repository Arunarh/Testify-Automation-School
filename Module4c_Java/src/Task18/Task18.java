package Task18;

import java.util.Scanner;

public class Task18 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your age: ");

        try {
            int age = input.nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid integer for your age.");
        }
    }
}