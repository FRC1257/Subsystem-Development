package frc.robot.subsystems.shooter;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {

  @AutoLog
  public static class ShooterIOInputs {
    public Angle leftFlywheelPositionRotations;
    public AngularVelocity leftFlywheelVelocity;
    public Voltage leftFlywheelAppliedVolts;
    public Current leftFlywheelOutputCurrent;

    public Angle rightFlywheelPositionRotations;
    public AngularVelocity rightFlywheelVelocity;
    public Voltage rightFlywheelAppliedVolts;
    public Current rightFlywheelOutputCurrent;
  }

  default void updateInputs(ShooterIOInputs inputs) {}

  default void setRPM(AngularVelocity leftRPM, AngularVelocity rightRPM) {}

  default void setRPM(AngularVelocity rpm) {
    setRPM(rpm, rpm);
  }

  default void setVoltage(Voltage leftVolts, Voltage rightVolts) {}

  default void setVoltage(Voltage volts) {
    setVoltage(volts, volts);
  }

  default void stop() {}

  default void setPIDGains(double Kp, double Ki, double Kd) {}

  default void setFeedForwardGains(double Ks, double Kv, double Ka) {}
}
