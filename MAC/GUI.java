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
     * this displays an error message on the screen for when the user screws up too many times
     */
    public void errorMessage()
    {
        frame.getContentPane().removeAll(); // clear the frame just in case
        JLabel label = new JLabel("INTRUDER ALRERT!"); // label with text "INTRUDER ALERT"
        SpringLayout layout = new SpringLayout(); // SpringLayout is the hardest layout but its easy to move around objects freely
        frame.setLayout(layout); // set the layout to SpringLayout
        Container contentPane = frame.getContentPane(); // get the contentPane to know where to move the objects
        frame.add(label); // add the label to the frame
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane); // move the x location of the label
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane); // move the y location of the label
        frame.revalidate(); // this lets the frame recognize the changes
        frame.repaint(); // this lets the frame add the changes
    }
    
    /**
     * creates a pop that displays the error text when the user messes up
     */
    public void uhOhPop()
    {
        // this creates a pop up on the frame with the alarm activated text on it with the header showing attempts left and the message having an error logo on it
        JOptionPane.showMessageDialog(frame, "Alarm activated! You are an Intruder!", "0 attempts left!", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This is used to get the amount of attempts left
     */
    public int getAttempts()
    {
        int ret = attempts; // hold the value of attempts
        attempts++; // increase attempts (an attempt has passed)
        return ret; // return the original value of attempts
    }
    
    /**
     * creates a pop that displays the good test when the user does good
     */
    public void unLockPop()
    {
        String text = new String("Welcome Back!"); // a String holding the text to display
        // this creates a pop up on the frame with the text "Welcome Back" with the header showing that the lock is unlocked and the message having no logo
        JOptionPane.showMessageDialog(frame, text, "Lock unlocked!", JOptionPane.PLAIN_MESSAGE);
    }
}
