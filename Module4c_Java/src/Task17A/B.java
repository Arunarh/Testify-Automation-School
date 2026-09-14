package Task17A;

public class B extends A {

    @Override
    public void enterUsername() {
        System.out.println("Username field is available.");
    }

    @Override
    public void enterPassword() {
        System.out.println("Password field is available.");
    }

    @Override
    public void forgotPassword() {
        System.out.println("Forgot Password option is available.");
    }

    @Override
    public void signIn() {
        System.out.println("Sign-in button is available.");
    }

    public static void main(String[] args) {

        B loginPage = new B();

        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.forgotPassword();
        loginPage.signIn();
    }
}