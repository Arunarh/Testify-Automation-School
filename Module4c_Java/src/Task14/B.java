package Task14;

public class B {

    public static void main(String[] args) {

        // Create an object of Class A
        A square = new A();

        // Set the length and breadth
        square.setLength(10);
        square.setBreadth(5);

        // Get the length and breadth
        double length = square.getLength();
        double breadth = square.getBreadth();

        // Calculate the area
        double area = length * breadth;

        // Print the final calculation
        System.out.println("The area of a square of length: " + length
                + " and breadth " + breadth + " is " + area);
    }
}