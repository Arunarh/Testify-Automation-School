package Task17B;

public class B implements A {

    @Override
    public void verifyUsername(String username) {
        System.out.println("Correct username entered: " + username);
    }

    @Override
    public void verifyPassword(String username, String password) {
        System.out.println("Password matches username " + username + ": " + password);
    }

    @Override
    public void verifyLogin(String username, String password) {
        System.out.println("Login successful for " + username + ". Dashboard opened.");
    }

    public static void main(String[] args) {

        B tester = new B();

        tester.verifyUsername("Michael");
        tester.verifyPassword("Michael", "Password123");
        tester.verifyLogin("Michael", "Password123");
    }
}