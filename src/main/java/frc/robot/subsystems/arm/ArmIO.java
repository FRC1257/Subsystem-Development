package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

public class ArmIO {
  @AutoLog
  public class ArmIOInputs {}

  public default void updateinputs(ArmIOInputs inputs) {}

  public default void setVoltage(double volts) {}

  public default void setPosition(double angle) {}
}
