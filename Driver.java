import java.io.*;


/**
 * Write a description of class Driver here.
 *
 * @author 
 * @version (a version number or a date)
 */
public class Driver
{
    public static void main(String[] args)
    {
        File f = new File("saveData.txt");    
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