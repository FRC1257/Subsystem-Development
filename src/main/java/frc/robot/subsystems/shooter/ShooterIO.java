package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

public interface ShooterIO {

  class ShooterIOInputs {
    public double leftShooterPositionRotations = 0.0;
    public double leftFlywheelVelocityRPM = 0.0;
    public double leftFlywheelAppliedVolts = 0.0;
    public double leftFlywheelOutputCurrent = 0.0;

    public double rightFlywheelPositionRotations = 0.0;
    public double rightFlywheelVelocityRPM = 0.0;
    public double rightFlywheelAppliedVolts = 0.0;
    public double rightFlywheelOutputCurrent = 0.0;
    //2024 code
  }

  default void updateInputs(ShooterIOInputs inputs) {}

  default void setVoltage(double leftVoltage, double rightVoltage) {}

  default void setPIDGains(double kP, double kI, double kD) {}

  default void setRPM(double leftRpm, double rightRpm) {}

  default void setFeedForwardGains(double kS, double kV, double kA) {}
}
