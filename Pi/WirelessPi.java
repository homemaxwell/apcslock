import java.io.*;
import java.net.*;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 * @author Maxwell Chang
 * @version (1)
 */
public class WirelessPi {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String serverAddress = "192.168.1.25";      //address of client
        BufferedReader input = null;
        ServerSocket listener = new ServerSocket(9090);
        String in = null;
        Socket socket = null;
        Trigger trigger = new Trigger();
        try {
            while (in == null) {
                socket = listener.accept();
                try {
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } 
                finally {
                    in = input.readLine();
                    socket.close();
                }
            }
            if(in.equals("1"))
            {
                System.out.println("unlock" );
                trigger.unlock();
            }
            if(in.equals("0"))
            {
                System.out.println("lock");
                trigger.lock();
            }
            if(in.equals("2"))
            {
                System.out.println("alarm");
                trigger.alarm();
            }
        }
        finally {
            Thread.sleep(100);
            listener.close();
        }
    }
}
