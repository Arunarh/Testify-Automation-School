package Task7;

public class Task7 {
    public static void main(String[] args) {

        String[][] fruits = new String[4][3];

        fruits[0][0] = "Apple";
        fruits[1][0] = "Mango";
        fruits[2][0] = "Orange";
        fruits[3][0] = "Banana";

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(fruits[row][column] + "\t");
            }
            System.out.println();
        }
    }
}