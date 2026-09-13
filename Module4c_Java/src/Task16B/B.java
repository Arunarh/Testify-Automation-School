package Task16B;

public class B extends A {

    @Override
    public void login(String username) {
        System.out.println("Child class: Welcome " + username + ", login successful!");
    }

    @Override
    public void logout(String username) {
        System.out.println("Child class: Goodbye " + username + ", you have logged out successfully!");
    }

    public static void main(String[] args) {
        B user = new B();

        user.login("Michael");
        user.logout("Michael");
    }
}