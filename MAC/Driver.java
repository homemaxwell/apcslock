import java.io.*;


/**
 * Runs the lock program
 *
 * @author Yaman Chaudhary
 * @version 1
 */
public class Driver
{
    public static void main(String[] args)
    {
        /* gets file "saveData.txt" which contains usernam, password, and security questions 
        if the program has been run before  */
        File f = new File("saveData.txt");  
        
        // if the file was found (meaning the program has been run before thus do not ask for new username and password)
        if (f.exists())
        {
            UserInteraction i = new UserInteraction("saveData.txt"); // constructs without activating firstUser
        }
        // if the file doesn't exist, then it is the user's first time, which means that they need to set up an account 
        else
        {
            UserInteraction e = new UserInteraction(); // constructs with activating firstUser
        }
    }
}
