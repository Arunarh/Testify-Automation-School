package Task19D;

public class A {

    String name = "Delta";

    public void printName(String userName) {
        System.out.println(this.name + " " + userName);
    }

    public static void main(String[] args) {

        A person = new A();

        person.printName("Michael");
    }
}