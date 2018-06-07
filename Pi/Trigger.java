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
        int positionNumber = 5; // 25% duty cycle turns motor clockwise
        
        if(positionNumber >= 0 && positionNumber <= 20)
        {
            value = positionNumber; // positionValue is stored in 
            SoftPwm.softPwmWrite(PinNumber, value + 5); // PinNumber refers to the GPIO pin on raspberryPi, moves the servo 90 degrees
            
            try
            {
                Thread.sleep(timeMS); // time delay between moving and stopping
            }
            catch(InterruptedException e){}
            
            SoftPwm.softPwmWrite(PinNumber, 0); // PMW signal stops, therefore the servo stops moving
        }
    }

    /**
     * lock - locks the lock
     */
    public void lock()
    {
        int positionNumber = 15; // duty cycle is 75% turns the servo counterclockwise
        
        if(positionNumber >= 0 && positionNumber <= 20) 
        {
            value = positionNumber; // PinNumber refers to the GPIO pin on raspberryPi, moves the servo -90 degrees
            SoftPwm.softPwmWrite(PinNumber, value + 5);
            
            try
            {
                Thread.sleep(timeMS); // time delay between moving and stopping
            }
            catch(InterruptedException e){}
            
            SoftPwm.softPwmWrite(PinNumber, 0); // PMW signal stops, therefore the servo stops moving
        }
    }
    
    /**
     * isLock - checks status of servo motor
     * @return returns true and false based on the status of the servo
     */
    public boolean isLock() {
        boolean status = true; //lock starts in lock position
        if(value != 15) //if it is unlocked it will store the status as false
            status = false;
        
            
        return status; 
    }

    
    /**
     * errorSys - triggers speakers when met with error, if lock is unlocked, then it will lock the system
     */
    public void alarm()
    {
            
        System.out.println("Evil! evil! intruder alert!");
        File f = new File("alarm.wav"); //imports .wav into File object f
        
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  //  
            Clip clip = AudioSystem.getClip(); 
            clip.open(audioIn); 
            clip.start(); // starts playing the alarm sound
        }
        catch(Exception ex){}
    }
}

