
/**
 * Write a description of class UserInteraction here.
 *
 * @author (Max McLoughlin)
 * @version (a version number or a date)
 */
import chn.util.*;
import java.util.*;
public class UserInteraction
{
    private String username;
    private String password;
    private String fileName;
    private FileOutput save;
    private ArrayList<String> securityQuestions;
    private ArrayList<String> securityAnswers;
    
    /**
     * constructs a userInteraction with a name, password, and a filename to save the data in.
     */
    public UserInteraction(String name, String pass, String file)
    {
        username = name;
        password = pass;
        fileName = file;
        save = new FileOutput(fileName);
        securityQuestions = new ArrayList<String>();
        securityAnswers = new ArrayList<String>();
    }
   
  
    /**
     * constructs a userInteraction with a username and a password.
     */
   public UserInteraction(String name, String pass)
    {
        username = name;
        password = pass;
        fileName = "saveData.txt";
        save = new FileOutput(fileName);
        securityQuestions = new ArrayList<String>();
        securityAnswers = new ArrayList<String>();
    }
    
    /**
     * constructs a UserInteraction without a username or a password.
     */
    public UserInteraction()
    {
        username = "";
        password = "";
        fileName = "saveData.txt";
        save = new FileOutput(fileName);
        securityQuestions = new ArrayList<String>();
        securityAnswers = new ArrayList<String>();
    }
    
    /**
     * Adds a security question and answer to their respective arraylists.
     */
    public void addSecurityQuestion()
    {
        ConsoleIO kb = new ConsoleIO();
        System.out.println("Please enter a security question: ");
        securityQuestions.add(kb.readLine());
        System.out.println("Please enter the answer: ");
        securityAnswers.add(kb.readLine());
    }
    
    /**
     * prompts the user with entering a username and a password.
     */
    public void firstUser()
    {
        ConsoleIO kb = new ConsoleIO();
        String input;
        System.out.print("Welcome. Please enter a username: ");
        username = kb.readLine();
        System.out.print("Please enter a password: ");
        password = kb.readLine();
        do
        {
            addSecurityQuestion();
            System.out.print("Would you like to add more? [Y/N] : ");
            input = kb.readLine();
        }while(input.equalsIgnoreCase("y"));
    }
    
    /**
     * If the user has security questions setup, the method prompts them a security question at random. 
     * If they answer correctly, they are able to reset the password. 
     * If they do not have any security questions, the user is to enter the username correctly in place of 
     * a security question. If the user fails to enter the username or answer correctly 3 times, the system locks.
     */
    public void forgotPassword()
    {
        ConsoleIO kb = new ConsoleIO();
        String testUser;
        int failedAttempts = 0;
        if(securityQuestions.size() == 0)      
        {
            System.out.print("Please enter your current username: ");
            testUser = kb.readLine();
            while(failedAttempts < 2 && !testUser.equals(username))
            {
                
                System.out.print("\nIncorrect. Please try again (remaining attempts: " + (2-failedAttempts) + "): ");
                testUser = kb.readLine();
                if(!testUser.equals(username))
                    failedAttempts++;
                
            }
            
            if(failedAttempts == 2)     //safeguards against guessing
            {
                System.out.println("\nSYSTEM LOCKED");
            }
            
            else
            {
                System.out.print("Please enter a new password: ");
                password = kb.readLine();
            }
        }
        else
        {
            int question = (int)(Math.random()*securityQuestions.size());   //picks a question at random
            String testAns;
            System.out.println(securityQuestions.get(question));
            System.out.print("Answer: ");
            testAns = kb.readLine();
            while(failedAttempts < 2 && !testAns.equals(securityAnswers.get(question)))
            {
                
                System.out.print("Incorrect. Please try again (remaining attempts: " + (2 - failedAttempts) + "): ");
                testAns = kb.readLine();
                if(!testAns.equals(securityAnswers.get(question)))
                    failedAttempts++;
            }
            if(failedAttempts == 2)     //safeguards against guessing
            {
                System.out.println("\nSYSTEM LOCKED");
            }
            else
            {
                System.out.print("Please enter a new password: ");
                password = kb.readLine();
            }
        }
    }
    
    /**
     * writes the username and encrypted password to a file
     */
    public void saveData()
    {
        save.println(encrypt(username));
        save.println(encrypt(password).toString());
        for(int i = 0; i < securityQuestions.size(); i++)
        {
            save.println(encrypt(securityQuestions.get(i)));
            save.println(encrypt(securityAnswers.get(i)));
        }
        save.close();
    }
    
    /**
     * reads data from the file and displays to the screen
     */
    public void readData()
    {
        FileInput read = new FileInput(fileName);
        String name = decrypt(read.readLine());
        String pass = decrypt(read.readLine());
        ArrayList newQuestions = new ArrayList();
        ArrayList newAnswers = new ArrayList();
        int i = 0;
        while(read.hasMoreLines())
        {
            newQuestions.add(decrypt(read.readLine()));
            newAnswers.add(decrypt(read.readLine()));
        }
        System.out.println(name);
        System.out.println(pass);
        securityQuestions = new ArrayList(newQuestions);
        securityAnswers = new ArrayList(newAnswers);
    }
    
    /**
     * takes input from the user, if the inputs are correct, the system opens, if not, the system locks.
     */
    public void input()
    {
        String testUser;
        String testPass = "";
        int failedAttempts = 0;
        ConsoleIO kb = new ConsoleIO();
        
        System.out.print("Please enter your username: ");
        testUser = kb.readLine();
        while(failedAttempts < 3 && !testUser.equals(username))
        {
            failedAttempts++;
            System.out.print("\nIncorrect. Please try again (remaining attempts: " + (3-failedAttempts) + "): ");
            testUser = kb.readLine();
        }
            
        if(failedAttempts == 3)     //safeguards against guessing
        {
            System.out.println("\nSYSTEM LOCKED");
        }
            
        else
        {
            System.out.print("Please enter your password: ");
            testPass = kb.readLine();
            failedAttempts = 0;
            while(failedAttempts < 3 && !testPass.equals(password))
            {
                failedAttempts++;
                System.out.print("\nIncorrect. Please try again (remaining attempts: " + (3-failedAttempts) + "): ");
                testPass = kb.readLine();
            }
            
            if(failedAttempts == 3)     //safeguards against guessing
            {
                System.out.println("\nSYSTEM LOCKED");
            }
            else
                System.out.println("\nSYSTEM UNLOCKED");
        }
    }
    
    private String encrypt(String s)
    {
        char[] c = s.toCharArray();
        char[] c2 = new char[c.length*2];
        
        for(int i = 0; i< c.length; i++)
            c[i] = (char)((int)(c[i]) - 27);        //offsets the ASCII characters
            
        for(int i = 0; i < c.length; i++)   
            c2[i*2] = c[i];                         //the characters in c fill the even indexes in c2
            
        for(int i = 0; i < c2.length; i++)
            if(i%2 == 1)
                c2[i] = (char)((int)(Math.random() * 32 + 65));     //the odd indexes in c2 are filled with random characters
       
        return new String(c2);      
    }
    
    private String decrypt(String s)
    {
        char[] c = s.toCharArray();
        char[] ret = new char[c.length/2];
        int retIterator = 0;
        for(int i = 0; i < c.length; i ++)
        {
            if(i%2 == 0)            //looks at the even indexes, where the actual characters are stored
            {
                ret[retIterator] = (char)((int)(c[i]) + 27);        //resets the characters to their original form
                retIterator++;
            }
        }
        return new String(ret);
    }
    
}
