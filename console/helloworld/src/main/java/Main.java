import java.io.Console ;

class Main
{
    public static void main(String args[])
    {
        Console c = System.console();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("Enter password: ");
        char[] ch = c.readPassword();
        String pass = String.valueOf(ch); //converting char array into string
        System.out.println("Password is: " + pass);
    }
}