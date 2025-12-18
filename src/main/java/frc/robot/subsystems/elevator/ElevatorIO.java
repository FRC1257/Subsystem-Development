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

    default void setVoltage(double voltage) {}
    // Getter method for target height
    default void getSetpoint() {
        return 0.0;
    }
   
    // Getter method for current position
    default void getPosition() {
        return 0.0;
    }

    default void updateInputs(ElevatorIOInputs inputs) {}
    // Getter method for velocity
    default void getVelocity(){
        return 0.0;
    }

    default void setRPM(double rpm){}
    // Checking if elevator goes above limit height
    default boolean isLimitSwitchPressed() {
        return false;
    }

    default void setFeedForwardGains(double kS, double kV, double kA) {}

    // Subsystem API (Used by commands)
   
    
    // The target height in meters
    default void setSetpoint(double setpoint) {}

    // Profiled PID to go to setpoint
    public default void goToSetpoint() {}

    // Returns true if elevator at setpoint
    public default boolean atSetpoint() {
        return false;
    }

    // Setter for voltage 
    default void setVoltage(double voltage) {}



}   

  
    