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
    public Angle topShooterPositionRotations = Units.Radians.zero();
    public AngularVelocity topFlywheelVelocity = Units.RPM.zero();
    public Voltage topFlywheelAppliedVolts = Units.Volts.zero();
    public Current topFlywheelOutputCurrent = Units.Amp.zero();

    public Angle bottomShooterPositionRotations = Units.Radians.zero();
    public AngularVelocity bottomFlywheelVelocity = Units.RPM.zero();
    public Voltage bottomFlywheelAppliedVolts = Units.Volts.zero();
    public Current bottomFlywheelOutputCurrent = Units.Amp.zero();
  }

  default void updateInputs(ShooterIOInputs inputs) {}

  default void setRPM(AngularVelocity rpm) {}

  default void setVoltage(Voltage volts) {}

  default Voltage getVoltage() {
    return Units.Volts.zero();
  }

  default void setPIDGains(double Kp, double Ki, double Kd) {}

  default void setFeedForwardGains(double Ks, double Kv, double Ka) {}
}
