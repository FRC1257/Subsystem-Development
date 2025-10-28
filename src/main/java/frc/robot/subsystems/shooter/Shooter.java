package frc.robot.subsystems.shooter;

import java.util.function.Supplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final ShooterIO shooterIO;
  private final ShooterIOInputsAutoLogged shooterInputs = new ShooterIOInputsAutoLogged();

  public Shooter(ShooterIO io) {
    this.shooterIO = io;
  }

  @Override
  public void periodic() {
    shooterIO.updateInputs(shooterInputs);
  }

  public Command runVoltageCommand(Supplier<Voltage> voltage) {
    return run(() -> shooterIO.setVoltage(voltage.get())).withName("Voltage");
  }

  public Command runRPMCommand(Supplier<AngularVelocity> rpm) {
    return run(() -> shooterIO.setRPM(rpm.get())).withName("RPM");
  }

  public Command stopCommand() {
    return run(() -> shooterIO.setVoltage(Units.Volts.zero())).withName("Stop");
  }
}
