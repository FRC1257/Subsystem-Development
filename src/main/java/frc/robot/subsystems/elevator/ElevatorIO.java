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
        public boolean limitSwitchPressed = false;
     }


    default void setVoltage(double voltage) {}

    default void updateInputs(ElevatorIOInputs inputs) {}

    default void setRPM(double rpm){}

    default void setFeedForwardGains(double kS, double kV, double kA) {}

    default void setSetpoint(double setpoint) {}
}   
