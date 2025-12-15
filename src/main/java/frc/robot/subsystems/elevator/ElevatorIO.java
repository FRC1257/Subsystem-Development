package frc.robot.subsystems.elevator;
import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
//motor is neos, we still need motor methods

public interface ElevatorIO {
    // Hardware API (Used by SparkMax)
    
    
    @AutoLog
    public static class ElevatorIOInputs {
        public double setpointMeters = 0.0;
        public double positionMeters = 0.0;
        public double velocityMetersPerSec = 0.0;
        public double appliedVoltage = 0.0;
        public boolean limitSwitchPressed = false;
     }

    
    default void updateInputs(ElevatorIOInputs inputs) {}
    default void setPIDGains(double kP, double kI, double kD) {}
    default void setRPM(double rpm){}
    default void setFeedForwardGains(double kS, double kV, double kA) {}
}
