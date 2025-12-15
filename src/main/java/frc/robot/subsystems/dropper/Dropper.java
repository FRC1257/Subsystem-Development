package frc.robot.subsystems.dropper;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

public class Dropper extends SubsystemBase {
  private final DropperIO io;
  private final DropperIOInputsAutoLogged inputs = new DropperIOInputsAutoLogged();

  public Dropper(DropperIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Dropper", inputs);
  }

  public Command runVoltage(DoubleSupplier voltage) {
    return new RunCommand (() -> io.setVoltage(voltage), this).withName("Dropper Voltage");
  }


  public Command stop() {
    return runOnce(() -> io.stop().withName("Dropper Stop"));
  }

  public void setPIDGains(double kP, double kI, double kD) {
    io.setPIDGains(kP, kI, kD);
  }
}
