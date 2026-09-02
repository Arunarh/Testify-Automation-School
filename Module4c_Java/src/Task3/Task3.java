package Task3;

public class Task3 {
    public static void main(String[] args) {

        int age = 41;

        // Method 1: Using + operator
        System.out.println("My age is " + age);

        // Method 2: Using concat()
        System.out.println("My age is ".concat(String.valueOf(age)));
    }
}