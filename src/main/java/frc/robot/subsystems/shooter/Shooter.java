package frc.robot.subsystems.shooter;

import static frc.robot.subsystems.shooter.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
  private final ShooterIO io;

  public Shooter(ShooterIO io) {
    this.io = io;
  }
  public void periodic() {}

  public Command runVoltage(DoubleSupplier leftVoltage, DoubleSupplier rightVoltage) {
    return new RunCommand(() -> io.setVoltage(leftVoltage.getAsDouble(), rightVoltage.getAsDouble()));
  }

  public Command runRPM(DoubleSupplier leftRpm, DoubleSupplier rightRpm) {
    return new RunCommand(() -> io.setRPM(leftRpm.getAsDouble(), rightRpm.getAsDouble()));
  }
}
