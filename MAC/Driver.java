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
        File f = new File("saveData.txt");  // gets file "saveData.txt" which contains usernam, password, and security questions if the program has been run before  
        if (f.exists())
        {
            UserInteraction i = new UserInteraction("saveData.txt");
        }
        else
        {
            UserInteraction e = new UserInteraction();
        }
    }
}
