/**
 * Write a description of class DateServer here.
 *
 * @author (Maxwell Chang, Max McLoughlin)
 * @version (1)
 */
import java.io.*;
import java.net.*;
import sun.audio.*;
import javax.sound.sampled.*;
public class WirelessHandler
{
    private String serverAddress = "192.168.1.29";      //client address
    
    /**
     * default constructor
     */
    public WirelessHandler()
    {
    }
    
    /**
     * sends the open signal to the raspberry pi
     */
    public void open() throws IOException 
    {
        ServerSocket listener = new ServerSocket(9090);
        
        
        Socket socket = new Socket(serverAddress,9090);
        try
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(1);
            socket.close(); //closes connection
        }
        finally
        {
            listener.close();
        }
        
        
    }
    
    /**
     * sends the lock signal to the raspberyy pi
     */
    public void lock() throws IOException
    {
        ServerSocket listener = new ServerSocket(9090);
        
            Socket socket = new Socket(serverAddress,9090);
            try
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(0);

                socket.close(); //closes connection
            }
            finally
            {
                
            }
        
        
    }
    
    /**
     * sends the alarm signal to the raspberry pi
     */
    public void alarm() throws IOException
    {
        ServerSocket listener = new ServerSocket(9090);
        
            Socket socket = new Socket(serverAddress,9090);
            try
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(2);

                socket.close(); //closes connection
                
                File f = new File("alarm");
        
                try{
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                }
                catch(Exception ex){}
            }
            finally
            {
                //socket.close();
            }
        
        
    }
}
