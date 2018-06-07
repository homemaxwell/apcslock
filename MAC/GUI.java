import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * GUI holds standard functions that a GUI needs to do
 *
 * @author Yaman Chaudhary
 * @version 1
 */
public abstract class GUI
{
    String username; // holds the username of the user
    String password; // holds the password of the user
    int attempts = 1; // how many wrong attempts does the user have in guessing the password
    JFrame frame; // the frame for the program box
    
    /**
     * standard constructor for the GUI, takes in a username and a password from the user and 
     * initializes the frame to get ready for operations
     *
     * @param user the username
     * @param passw the password
     */
    public GUI(String user, String passw)
    {
         frame = new JFrame("Lock"); // creates the frame
         username = user; // initialize the username
         password = passw; // initialize the password
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this makes the red x on the frame close the program
         frame.setSize(500, 500); // creates the frame as a 500 by 500 box
    }
    
    /**
     * clears everything on the frame
     */
    public void clearAll()
    {
        frame.getContentPane().removeAll();
    }
    
    /**
     * creates a pop that displays the attempts left
     *
     * @param atts the number of attempts the user has taken
     */
    public void attemptsPop(int atts)
    {
        // this creates a pop up on the frame with the text "Incorrect Password or Username" with the header showing attempts left and the message having an error logo on it
        JOptionPane.showMessageDialog(frame, "Incorrect Password or Username. ", 3 - atts + " attempts left.", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     *
     *
     *
     *
     */
    public void errorMessage()
    {
        frame.getContentPane().removeAll();
        JLabel label = new JLabel("INTRUDER ALRERT!");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        Container contentPane = frame.getContentPane();
        frame.add(label);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);
        frame.revalidate();
        frame.repaint();
    }
    
    public void uhOhPop()
    {
        JOptionPane.showMessageDialog(frame, "Alarm activated! You are an Intruder!", "0 attempts left!", JOptionPane.ERROR_MESSAGE);
    }
    
    public int getAttempts()
    {
        int ret = attempts;
        attempts++;
        return ret;
    }
    
    public void unLockPop()
    {
        String text = new String("Welcome Back!");
        JOptionPane.showMessageDialog(frame, text, "Lock unlocked!", JOptionPane.PLAIN_MESSAGE);
    }
}
