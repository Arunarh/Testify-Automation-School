package Task8;

import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the principal amount: ");
        double principal = input.nextDouble();

        System.out.print("Enter the interest rate: ");
        double rate = input.nextDouble();

        System.out.print("Enter the time in years: ");
        double time = input.nextDouble();

        double simpleInterest = (principal * rate * time) / 100;

        System.out.println("The simple interest is: " + simpleInterest);

        input.close();
    }
}