package Task19C;

public class B extends A {

    String name = "Anderson";

    public void printNames() {
        System.out.println("Class A name: " + super.name);
        System.out.println("Class B name: " + name);
    }

    public static void main(String[] args) {

        B person = new B();

        person.printNames();
    }
}