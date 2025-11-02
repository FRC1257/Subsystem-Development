package frc.robot.subsystems.shooter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;

public class Shooter extends SubsystemBase {
  private final ShooterIO io;
  private final ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();

  public Shooter(ShooterIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Shooter", inputs);
  }

  public double getVoltage() {
    return io.getVoltage();
  }

  public Command runVoltage(DoubleSupplier voltage) {
    return run(() -> io.setVoltage(MathUtil.clamp(voltage.getAsDouble(), -12, 12)))
        .withName("Cool Voltage Command");
  }

  public Command runRPM(DoubleSupplier rpm) {
    return run(() -> io.setRPM(rpm.getAsDouble())).withName("Cool RPM Command");
  }

  public void setPIDGains(double Kp, double Ki, double Kd) {
    io.setPIDGains(Kp, Ki, Kd);
  }
}
