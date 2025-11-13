package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
  
  @AutoLog
  public class ArmIOInputs {
    public double angleRads = 0.0;
    public double angVelocityRadsPerSec = 0.0;
    public double appliedVolts = 0.0;
    public double setpointAngleRads = 0.0;
  }

  public default void updateInputs(ArmIOInputs inputs) {}

  public default void setVoltage(double volts) {}

  public default void setPosition(double angle) {}

  public default void stop() {}
}
