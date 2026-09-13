package Task15;

public class B extends A {

    public void methodFour() {
        System.out.println("This is method four from child class B.");
    }

    public void methodFive() {
        System.out.println("This is method five from child class B.");
    }

    public static void main(String[] args) {

        B object = new B();

        object.methodOne();
        object.methodTwo();
        object.methodThree();
        object.methodFour();
        object.methodFive();
    }
}