package frc.robot.subsystems.Arm;

import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
  @AutoLog // creates the class for us
  public class ArmIOInputs {}

  public default void updateInputs(ArmIOInputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default void setPosition(double angle) {}
}
