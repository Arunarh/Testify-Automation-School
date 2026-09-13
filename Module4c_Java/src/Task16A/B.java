package Task16A;

public class B {

    public static void main(String[] args) {

        A test = new A();

        test.runTest("Login Test");
        test.runTest("Login Test", 1);
        test.runTest(2);
        test.runTest("Payment Test", 3, true);
        test.runTest(4, "Logout Test", true);
    }
}