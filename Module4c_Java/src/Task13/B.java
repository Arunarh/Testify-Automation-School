package Task13;

public class B {

    public static void main(String[] args) {

        // Object 1 uses Constructor 1
        A object1 = new A();

        // Object 2 uses Constructor 2
        A object2 = new A("Michael");

        // Object 3 uses Constructor 3
        A object3 = new A("Michael", 41);
    }
}