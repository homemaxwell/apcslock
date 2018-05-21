import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;


public class Trigger {
    /**
     * public static void main(String[] args) throws InterruptedException {
     * <p>
     * ConsoleIO kb = new ConsoleIO();
     * String input = "";
     * <p>
     * GpioController gpio = GpioFactory.getInstance();
     * Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_01, args);
     * GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);
     * com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
     * com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
     * com.pi4j.wiringpi.Gpio.pwmSetClock(500);
     * pwm.setPwm(500);
     * console.println("PWM rate is: " + pwm.getPwm());
     * console.println("Press ENTER to set the PWM to a rate of 250");
     * System.console().readLine();
     * pwm.setPwm(250);
     * console.println("PWM rate is: " + pwm.getPwm());
     * console.println("Press ENTER to set the PWM to a rate to 0 (stop PWM)");
     * System.console().readLine();
     * pwm.setPwm(0);
     * console.println("PWM rate is: " + pwm.getPwm());
     * gpio.shutdown();
     * }
     */
    public Trigger() {
        GpioController gpio = GpioFactory.getInstance();
        Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_01, args);
        GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);
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
            com.pi4j.wiringpi.Gpio.pwmSetRange();
            com.pi4j.wiringpi.Gpio.pwmsetClock();
            pwm.setPwm();
        }

    }

    public void lock(Object input)
    {
        if (input.isLock() == true)
        {
            //Set the duty cycle above 50% to turn clockwise
            com.pi4j.wiringpi.Gpio.pwmSetRange();
            com.pi4j.wiringpi.Gpio.pwmsetClock(.5);
            pwm.setPwm();
        }
    }
}

