 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class DateClient {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {
        String serverAddress = "192.168.1.25";
        Socket s = new Socket(serverAddress, 9090);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        if(answer.equals("0"))
        {
            System.out.println("Locked.");
        }
        
        else
        {
            if(answer.equals("1"))
                System.out.println("Unlocked.");
            else
            {
                System.out.println("ALARM.");
            }
        }
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);
    }
}