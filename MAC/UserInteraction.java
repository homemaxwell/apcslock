/**
 * Has all the carious methods to display various parts during the run
 *
 * @author Max McLoughlin & Yaman Chaudhary
 * @version 1
 */
import chn.util.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserInteraction extends GUI
{
    private String username; // holds the username
    private String password; // holds the password
    private String fileName; // holds the name of the file (saveData.txt)
    private ArrayList<String> securityQuestions; // has all of the security questions
    private ArrayList<String> securityAnswers; // has all of the answers, with matching indexes
    int count = 1; // used later for attempt counting
    private WirelessHandler green; // to connect through wi-fi 
    
    /**
     * constructs a userInteraction with a filename with data in it
     */
    public UserInteraction(String file)
    {
        super("", ""); // start off without anything
        green = new WirelessHandler(); // create the wi-fi connection
        fileName = file; // saveData.txt
        readData(); // read in the data
        saveData(); // save it right now to refill the saveData.txt
        lockScreen(); // open up the lockscreen
    } 
    
    /**
     * constructs a UserInteraction without a filename with data in it
     */
    public UserInteraction()
    {
        super("", ""); // nothing
        green = new WirelessHandler(); // wi-fi connection
        fileName = "saveData.txt"; 
        securityQuestions = new ArrayList<String>(); 
        securityAnswers = new ArrayList<String>();
        firstUser(); // opens up the first user screen (to create an "account")
    }
    
    /**
     * pretty much getAttempts from the parent class but needed for another attempt counter
     *
     * @return the attempt number so far
     */
    public int getCount()
    {
        int ret = count; 
        count++; 
        return ret;
    }
    
    /**
     * Adds a security question and answer to their respective arraylists, each index has the question and its answer
     */
    public void addSecurityQuestion()
    {
        clearAll();
        frame.setVisible(false); // hide the frame
        
        ConsoleIO kb = new ConsoleIO(); // input
        JLabel label = new JLabel("Enter a security question: "); // labels are just text
        JTextField question = new JTextField(15); // text fields have an empty box where the user can type
        JLabel label2 = new JLabel("Enter the answer: ");
        JTextField answer  = new JTextField(15);
        JButton done = new JButton("Submit"); // buttons are to click to do something else
        JButton more = new JButton("Add Another");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        
        // add it all to the frame
        frame.add(label);
        frame.add(question);
        frame.add(label2);
        frame.add(answer);
        frame.add(done);
        frame.add(more);
        
        // move them all to the correct location
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, question, 5, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, question, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, label2, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.WEST, answer, 5, SpringLayout.EAST, label2);
        layout.putConstraint(SpringLayout.NORTH, answer, 10, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.WEST, done, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, done, 10, SpringLayout.SOUTH, label2);
        layout.putConstraint(SpringLayout.WEST, more, 5, SpringLayout.EAST, done);
        layout.putConstraint(SpringLayout.NORTH, more, 10, SpringLayout.SOUTH, label2);
        

        frame.setVisible(true); // show the frame
        
        
        // action listener for the button, this waits until the user clicks the submit button
        done.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                securityQuestions.add(question.getText());
                securityAnswers.add(answer.getText());
                saveData();
                lockScreen();
            }

         });
         
        // action listener for the other button, waits until user clicks the Add Another button
        more.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                securityQuestions.add(question.getText());
                securityAnswers.add(answer.getText());
                addSecurityQuestion();
            }

         });
    }
    
    /**
     * prompts the user with entering a username and a password.
     */
    public void firstUser()
    {
        frame.setVisible(false); // hide the frame
        JLabel hi = new JLabel("We have determined that this is your first time using this lock!");
        JLabel words = new JLabel("Enter a username: ");
        JTextField name = new JTextField(15);
        JPasswordField pass = new JPasswordField(15); // password fields are text fields but hide the mesage inside
        JLabel passWord = new JLabel("Enter a password: ");
        JButton but = new JButton("Done");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        
        // add everything
        frame.add(hi); frame.add(words); frame.add(name); frame.add(pass); frame.add(passWord); frame.add(but);
        
        // place the items into the right locations
        layout.putConstraint(SpringLayout.WEST, hi, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, hi, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, words, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, words, 10, SpringLayout.SOUTH, hi);
        layout.putConstraint(SpringLayout.WEST, name, 5, SpringLayout.EAST, words);
        layout.putConstraint(SpringLayout.NORTH, name, 10, SpringLayout.SOUTH, hi);
        layout.putConstraint(SpringLayout.WEST, passWord, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, passWord, 10, SpringLayout.SOUTH, words);
        layout.putConstraint(SpringLayout.WEST, pass, 5, SpringLayout.EAST, passWord);
        layout.putConstraint(SpringLayout.NORTH, pass, 10, SpringLayout.SOUTH, words);
        layout.putConstraint(SpringLayout.WEST, but, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, but, 10, SpringLayout.SOUTH, pass);
        
        frame.setVisible(true); // show the frame
        
        // action listener for the Done button, clicked when user is done entering their info
        but.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                username = name.getText();
                password = new String(pass.getPassword());
                addSecurityQuestion();
            }

         });

    }

    /**
     * If the user has security questions setup, the method prompts them a security question at random. 
     * If they answer correctly, they are able to reset the password. 
     * If they do not have any security questions, the user is to enter the username correctly in place of 
     * a security question. If the user fails to enter the username or answer correctly 3 times, the system locks.
     */
    public void forgotPassword()
    {
        clearAll();
        int hold = getCount();
        frame.setVisible(false); // hide the frame
        int question = (int)(Math.random()*securityQuestions.size());   //picks a question at random
        JLabel q = new JLabel(securityQuestions.get(question));
        JLabel words = new JLabel("Enter the answer: ");
        JTextField ans = new JTextField(15);
        JButton but = new JButton("Done");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        
        // add everything to the frame
        frame.add(q); frame.add(words); frame.add(ans); frame.add(but);
        
        // places them in the correct locations
        layout.putConstraint(SpringLayout.WEST, q, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, q, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, words, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, words, 10, SpringLayout.SOUTH, q);
        layout.putConstraint(SpringLayout.WEST, ans, 5, SpringLayout.EAST, words);
        layout.putConstraint(SpringLayout.NORTH, ans, 10, SpringLayout.SOUTH, q);
        layout.putConstraint(SpringLayout.WEST, but, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, but, 10, SpringLayout.SOUTH, ans);
        
        frame.setVisible(true); // shows frame
        
        // action listener for the done button, clicked when user has entered the answer
        but.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ans.getText().equals(securityAnswers.get(question)))
                {
                    getNewPassword();
                    saveData();
                }
                else
                {
                    if (hold < 3)
                    {
                        attemptsPop(hold);
                        forgotPassword();
                    }
                    else
                    {
                        try 
                        {
                            green.alarm();
                        } catch (Exception ex) {
                            Logger.getLogger(WirelessHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        errorMessage();
                        uhOhPop();
                    }
                }
            
            }

         });
    }
    
    /**
     * prompts the user for a new password once they get the security question right
     */
    public void getNewPassword()
    {
        clearAll(); 
        frame.setVisible(false); // hide the frame
        JLabel words = new JLabel("Enter the new password: ");
        JPasswordField pass = new JPasswordField(15);
        JButton but = new JButton("Done");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        
        // add parts onto the frame
        frame.add(words); frame.add(pass); frame.add(but);
        
        // place them in the right spots 
        layout.putConstraint(SpringLayout.WEST, words, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, words, 5, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, pass, 5, SpringLayout.EAST, words);
        layout.putConstraint(SpringLayout.NORTH, pass, 10, SpringLayout.NORTH, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, but, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, but, 10, SpringLayout.SOUTH, words);
        
        frame.setVisible(true); // show frame
        
        // action listener for the done button, clicked when user is done entering a new password
        but.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                password = new String(pass.getPassword());
                saveData();
                lockScreen();
            }

         });
    }
    
    /**
     * writes the username and encrypted password to a file
     */
    public void saveData()
    {
        FileOutput save = new FileOutput(fileName);
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
        username = decrypt(read.readLine());
        password = decrypt(read.readLine());
        ArrayList newQuestions = new ArrayList();
        ArrayList newAnswers = new ArrayList();
        int i = 0;
        while(read.hasMoreLines())
        {
            newQuestions.add(decrypt(read.readLine()));
            newAnswers.add(decrypt(read.readLine()));
        }
        securityQuestions = newQuestions;
        securityAnswers = newAnswers;
    }
    
    /**
     * takes input from the user, if the inputs are correct, the system opens, if not, the system locks.
     */
    public void lockScreen()
    {
        clearAll(); 
        frame.setVisible(false); // hide the frame
        JTextField text = new JTextField(15); 
        JLabel label = new JLabel("Username: ");
        JButton done = new JButton("Submit");
        JButton forgot = new JButton("Forgot Password");
        JLabel label2 = new JLabel("Password: ");
        JPasswordField pass = new JPasswordField(15);
         
        Container contentPane = frame.getContentPane();
         
        SpringLayout layout = new SpringLayout();
        
        // adds the data and puts them in the correct locations
      
        frame.setLayout(layout);
        frame.add(label);
         
        frame.add(text);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);
         
        frame.add(done);
        layout.putConstraint(SpringLayout.WEST, text, 5, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, text, 5, SpringLayout.NORTH, contentPane);
         
        frame.add(label2);
        frame.add(pass);
        layout.putConstraint(SpringLayout.WEST, label2, 5, SpringLayout.WEST, contentPane);
         
        layout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.WEST, pass, 5, SpringLayout.EAST, label2);
        layout.putConstraint(SpringLayout.NORTH, pass, 10, SpringLayout.SOUTH, text);
        
        layout.putConstraint(SpringLayout.WEST, done, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, done, 10, SpringLayout.SOUTH, pass);
        
        frame.add(forgot);
        layout.putConstraint(SpringLayout.WEST, forgot, 5, SpringLayout.EAST, done);
        layout.putConstraint(SpringLayout.NORTH, forgot, 10, SpringLayout.SOUTH, pass);
         
        frame.setVisible(true); // show the frame
         
        // action listener for when the user forgot his password, clicks forgot button
        forgot.addActionListener(new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
                forgotPassword();
            }
        });
        
        // action listener for when the user wants to check if his username and password are correct (done button)
        done.addActionListener(new ActionListener(){

           @Override
           public void actionPerformed(ActionEvent e) {
               int hold = getAttempts();
               // if user is right
               if (text.getText().equals(username) && new String(pass.getPassword()).equals(password))
               {
                   try 
                   {
                        green.open();
                   } catch (Exception ex) {
                        Logger.getLogger(WirelessHandler.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   unLocktoLock();
                   unLockPop();
               }
               else
               {
                   // if less then 3 attempts have passed
                   if (hold < 3)
                   {
                       attemptsPop(hold);
                       frame.getContentPane().removeAll();
                       lockScreen();
                   }
                   // if more than 3 attempts have passed
                   else
                   {
                       try 
                       {
                            green.alarm();
                       } catch (Exception ex) {
                            Logger.getLogger(WirelessHandler.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       errorMessage();                        
                       uhOhPop();
                   }
               }
           }
        });
    }
    
    /*
     * 
     */
    private void unLocktoLock()
    {  
        JButton done = new JButton("Lock It");
        frame.getContentPane().removeAll();
        JLabel label = new JLabel("The lock is now open!");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        Container contentPane = frame.getContentPane();
        frame.add(label);
        frame.add(done);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, done, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, done, 10, SpringLayout.SOUTH, label);
        frame.revalidate();
        frame.repaint();
        
        // action listener for the done button, clicked when user is done with the lock and wants to close the lock again
        done.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                     green.lock();
                }
                catch (Exception ex)
                {
                     Logger.getLogger(WirelessHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

          });
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
