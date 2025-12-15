package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;

public class intake extends SubsystemBase {
  private final IntakeIOIO io;
  private final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

  public intake(IntakeIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Intake", inputs);
  }

  public Command runVoltage(DoubleSupplier voltage) {
    return run(() -> io.setVoltage(MathUtil.clamp(voltage.getAsDouble(), -12, 12)))
        .withName("runVoltage");
  }

  public Command runRPM(DoubleSupplier rpm) {
    return run(() -> io.setRPM(rpm.getAsDouble())).withName("runRPM");
  }

  public void setPIDGains(double Kp, double Ki, double Kd) {
    io.setPIDGains(Kp, Ki, Kd);
  }

  public void setDirection(boolean forward) {
    return run(() -> io.setDirection(forward).withName("setDirection"));
  }
  
  public Command stopCommand(){
    return runOnce(
    () -> io.stop()
    Logger.processInputs(intake, inputs); 
    )
  }
}