/**
 * Write a description of class UserInteraction here.
 *
 * @author (Max McLoughlin, Maxwell Chang)
 * @version (1)
 */
import com.pi4j.wiringpi.*;
import sun.audio.*;
import javax.sound.sampled.*;
import java.io.*;

public class Trigger {
    private int value;
    private int PinNumber = 1;
    private int timeMS = 300
    ;
    
    /**
      * default constructor, sets the pin to position 0 
    **/
    public Trigger() {
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(PinNumber, 0, 1000);
        value = 0;
    }

    /**
     * unlock - will unlock the lock
     */
    public void unlock() {
        int positionNumber = 5;
        
        if(positionNumber >= 0 && positionNumber <= 20)
        {
            value = positionNumber;
            SoftPwm.softPwmWrite(PinNumber, value + 5);
            
            try
            {
                Thread.sleep(timeMS);
            }
            catch(InterruptedException e){}
            
            SoftPwm.softPwmWrite(PinNumber, 0);
        }
    }

    /**
     * lock - locks the lock
     */
    public void lock()
    {
        int positionNumber = 15;
        
        if(positionNumber >= 0 && positionNumber <= 20)
        {
            value = positionNumber;
            SoftPwm.softPwmWrite(PinNumber, value + 5);
            
            try
            {
                Thread.sleep(timeMS);
            }
            catch(InterruptedException e){}
            
            SoftPwm.softPwmWrite(PinNumber, 0);
        }
    }
    
    /**
     * isLock - checks status of servo motor
     * @return returns true and false based on the status of the servo
     */
    public boolean isLock() {
        boolean status = true;
        if(value != 15)
            status = false;
        
            
        return status;
    }

    
    /**
     * errorSys - triggers speakers when met with error, if lock is unlocked, then it will lock the system
     */
    public void alarm()
    {
            
        System.out.println("Evil! evil! intruder alert!");
        File f = new File("alarm.wav");
        
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch(Exception ex){}
    }
}

