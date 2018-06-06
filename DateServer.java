
/**
 * Write a description of class DateServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
import java.net.*;
public class DateServer
{
    public void open() throws IOException
    {
        ServerSocket listener = new ServerSocket(9090);
        while(true)
        {
            Socket socket = listener.accept();
            try
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(1);
                
            }
            finally
            {
                socket.close();
            }
        }
        
    }
    
    public void lock() throws IOException
    {
        ServerSocket listener = new ServerSocket(9090);
        while(true)
        {
            Socket socket = listener.accept();
            try
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(0);
                
            }
            finally
            {
                socket.close();
            }
        }
        
    }
    
    public void alarm() throws IOException
    {
        ServerSocket listener = new ServerSocket(9090);
        while(true)
        {
            Socket socket = listener.accept();
            try
            {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(2);
                
            }
            finally
            {
                socket.close();
            }
        }
        
    }
}
