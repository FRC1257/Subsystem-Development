package frc.robot.subsystems.claw;
import org.littletonrobotics.junction.AutoLog;
//Idk how to do the auto importation and idk what to import


public interface ClawIO {
    @AutoLog
    public class ClawIOInputs {
        //voltage being applied to the motor, set to zero as a starting value
        public double appliedVoltage = 0.0
        //monitors draw of the motor for stalling, set to zero as a starting value
        public double currentAmps = 0.0
        //monitors temp of motor for overheating, set to zero as a starting value
        public double tempCelsius = 0.0
    }
    //empty methods that will be overriden in the other IO files
    public default void updateInputs(ClawIOInputs inputs) {}
    public default void open(double volts) {}
    public default void close(double volts) {}
    public default void stop() {}
}