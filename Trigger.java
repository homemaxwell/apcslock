import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;


public class Trigger {
    /**public static void main(String[] args) throws InterruptedException {

     ConsoleIO kb = new ConsoleIO();
     String input = "";

     GpioController gpio = GpioFactory.getInstance();
     Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_01, args);
     GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);
     com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
     com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
     com.pi4j.wiringpi.Gpio.pwmSetClock(500);
     pwm.setPwm(500);
     console.println("PWM rate is: " + pwm.getPwm());
     console.println("Press ENTER to set the PWM to a rate of 250");
     System.console().readLine();
     pwm.setPwm(250);
     console.println("PWM rate is: " + pwm.getPwm());
     console.println("Press ENTER to set the PWM to a rate to 0 (stop PWM)");
     System.console().readLine();
     pwm.setPwm(0);
     console.println("PWM rate is: " + pwm.getPwm());
     gpio.shutdown();
     }*/

    public void unlock()
    {
        if
    }
}
