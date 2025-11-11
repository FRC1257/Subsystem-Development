package frc.robot.subsystems.Arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
  @AutoLog // creates the class for us
  public class ArmIOInputs {
    public double angle;
    public double velocity;
    public double appliedVoltage;
  }

  public default void updateInputs(ArmIOInputs inputs) {}

  public default void setVoltage(double motorVolts) {}

  public default double getAngle() {
    return 0.0;
  }
  public default double getAngVelocity() {
    return 0.0;
  }




}
