import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class GUI
{
    String username;
    String password;
    boolean check;
    int attempts = 1;
    JFrame frame;
    
    public GUI(String user, String passw)
    {
         frame = new JFrame("Lock");
         username = user;
         password = passw;
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(500, 500);
    }
    
    public void clearAll()
    {
        frame.getContentPane().removeAll();
    }
    
    public void attemptsPop(int atts)
    {
        JOptionPane.showMessageDialog(frame, "Incorrect Password or Username. ", 3 - atts + " attempts left.", JOptionPane.ERROR_MESSAGE);
    }
    
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
        JOptionPane.showMessageDialog(frame, "Welcome Back, " + username, "Lock unlocked!", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void checkLock()
    {
        frame.getContentPane().removeAll();
        JLabel label = new JLabel("The lock is now open!");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        Container contentPane = frame.getContentPane();
        frame.add(label);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);
        frame.revalidate();
        frame.repaint();
    }
}