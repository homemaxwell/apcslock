import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;


public class Trigger {
    public Trigger() {
        GpioController gpio = GpioFactory.getInstance();
        //Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_01, args);
        GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(RaspiPin.Gpio_01, "Servo Motor", PinState.HIGH);
        com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
    }

    /**
     * unlock - will unlock the lock
     * @param input
     */
    public void unlock(Object input) {
        if (input.isLock() == false)
        {
            //Set the duty cycle below 50% to turn counterclockwise
            com.pi4j.wiringpi.Gpio.pwmSetRange(); //need to test values 
            com.pi4j.wiringpi.Gpio.pwmsetClock(); //need to test values
            pwm.setPwm();
            //Thread.sleep
        }
        //Set servo to neutral
        com.pi4j.wiringpi.Gpio.pwmSetRange(0);
        com.pi4j.wiringpi.Gpio.pwmSetClock(0);
        pwm.setPwm(0):
        
        //shutdown Gpio pins
        pwm.setShutdownOptions(true, PinState.LOW, PinPUllResistance.OFF);
        gpio.shutdown();
        gpio.unProvisionPin(pin);
    }

    /**
     * lock - locks the lock
     * @param input
     */
    public void lock(Object input)
    {
        if (input.isLock() == true)
        {
            //Set the duty cycle above 50% to turn clockwise
            com.pi4j.wiringpi.Gpio.pwmSetRange(); //need to test values
            com.pi4j.wiringpi.Gpio.pwmsetClock(.5); //need to test values
            pwm.setPwm();
        }
        //Set servo to neutral
        com.pi4j.wiringpi.Gpio.pwmSetRange(0);
        com.pi4j.wiringpi.Gpio.pwmSetClock(0);
        pwm.setPwm(0):
        
        //shutdown Gpio pins
        pwm.setShutdownOptions(true, PinState.LOW, PinPUllResistance.OFF);
        gpio.shutdown();
        gpio.unProvisionPin(pin);
        
    }
    
    /**
     * isLock - checks status of servo motor
     * @return returns true and false based on the status of the servo
     */
    public boolean isLock() {
        if(com.pi4j.wiringpi.Gpio.getpwm() == 500);
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
    
    /**
     * whichRot() - checks which direction the servo is spinning
     * @return direction - returns which direction the servo is spinning
     */
    public String whichRot()
    {
        String direction = "";
        if(com.pi4j.wiringpi.getpwm() == num);
        {
            direction = "counterclockwise";
        }
        else 
        {
            direction = "clockwise";
        }
        
        return direction;
    }
    
    /**
     * errorSys - triggers speakers and LED when met with error
     * @param error - error from UserInteractions() class
     */
    public void errorSys(UserInteractions error)
    {
        //instantiate objects
        GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "PinLED", PinState.Low); //GPIO_01 PIN 
        AudioInputStream audioInputStream = javax.sound.sampled.AudioSystem.getAudioInputStream(new java.io.File(x), getAbsoluteFile());
        if(error)
        {
            //LED
            pin.HIGH();
            pin.pulse(10000, true);
            
            //audio - plays .wav file
            java.sound.sampled.AudioSysten.getClip().open(audioInputStream);
            java.sound.sampled.AudioSystem.start();
        }
       
        System.out.println("ERROR");
    }
}

