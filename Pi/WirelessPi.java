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
        String serverAddress = "192.168.1.25";      // address of client
        BufferedReader input = null; // BufferedRead input gets data from the input stream or socket and stores it in input variable
        ServerSocket listener = new ServerSocket(9090); // ServerSocket listener will constantly listen to port 9090 inorder to receive input from MAC
        String in = null; // in is the string type variable to 
        Socket socket = null;
        Trigger trigger = new Trigger(); // instantiates object trigger, so that we can use functions from trigger class
        try {
            while (in == null) {
                socket = listener.accept(); 
                try {
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // tries to receive input from inputstream
                } 
                finally {
                    in = input.readLine(); // once the input is received it is stored in variable in
                    socket.close(); // closes socket and connection
                }
            }
            if(in.equals("1")) // if input receieved from socket is string "1" then it will trigger the unlock() method from trigger class
            {
                System.out.println("unlock" );
                trigger.unlock();
            }
            if(in.equals("0")) // if input received from socket is string "0" then it will trigger the lock() method from trigger class
            {
                System.out.println("lock");
                trigger.lock();
            }
            if(in.equals("2")) // if input received from scoekt is string "2" then it will trigger the alarm() method from trigger class
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
