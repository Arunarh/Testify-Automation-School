package Task16A;

public class A {

    // Overloading 1: different number of parameters
    public void runTest(String testName) {
        System.out.println("Running test: " + testName);
    }

    public void runTest(String testName, int testNumber) {
        System.out.println("Running test: " + testName + ", Test Number: " + testNumber);
    }

    // Overloading 2: different data types
    public void runTest(int testNumber) {
        System.out.println("Running Test Number: " + testNumber);
    }

    // Overloading 3: different order of parameter types
    public void runTest(String testName, int testNumber, boolean passed) {
        System.out.println("Test: " + testName + ", Number: " + testNumber + ", Passed: " + passed);
    }

    public void runTest(int testNumber, String testName, boolean passed) {
        System.out.println("Test Number: " + testNumber + ", Test: " + testName + ", Passed: " + passed);
    }
}