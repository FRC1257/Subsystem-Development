package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
  @AutoLog
  public static class ShooterIOInputs { // these were mostly copied from existing input classes
    public double velocityRPM = 0.0;
    public double appliedVoltage = 0.0;

    // arrays are used because we have multiple motors
    public double motorCurrent = 0.0;
  }

  public default void updateInputs(ShooterIOInputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default double getVoltage() {
    return 0;
  }

  public default void setRPM(double rpm) {}

  public default double getRPM() {
    return 0;
  }

  public default void setPIDGains(double Kp, double Ki, double Kd) {}
}
