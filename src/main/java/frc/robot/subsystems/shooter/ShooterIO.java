package frc.robot.subsystems.shooter;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {

  @AutoLog
  public static class ShooterIOInputs {
    public double ShooterPositionRotations;
    public double FlywheelVelocity;
    public double FlywheelAppliedVolts;
    public double FlywheelOutputCurrent;
  }

  default void updateInputs(ShooterIOInputs inputs) {}

  default void setRPM(AngularVelocity rpm) {}

  default void setVoltage(Voltage volts) {}

  default void setPIDGains(double Kp, double Ki, double Kd) {}

  default void setFeedForwardGains(double Ks, double Kv, double Ka) {}
}
