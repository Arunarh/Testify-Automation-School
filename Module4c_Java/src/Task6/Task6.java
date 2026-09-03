package Task6;

public class Task6 {
    public static void main(String[] args) {

        String word = "DEMOCRACY";

        String reversed = new StringBuilder(word).reverse().toString();

        String result = reversed.substring(4, 8);

        System.out.println("Reversed string: " + reversed);
        System.out.println("The word is: " + result);
    }
}