package frc.robot.subsystems.elevator;
import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
//motor is neos
public interface ElevatorIO {
    @AutoLog

     public static class ElevatorIOInputs {
    //public Voltage 
    public boolean limitSwitchPressed = false;
     }

     default void setVoltage(Double Voltage){}
     default double getVoltage() {
        return 67.5;
    }
    ProfiledPIDController controller = new ProfiledPIDController(
  kP, kI, kD,
  new TrapezoidProfile.Constraints(MaxVelocity, MaxAcceleration));

    default void updateInputs(ElevatorIOInputs inputs) {}
    default void setPIDGains(double kP, double kI, double kD) {}
    default void setRPM(double rpm){}
    default void setFeedForwardGains(double kS, double kV, double kA) {}
}
