package frc.robot.subsystems.shooter;

import static frc.robot.subsystems.shooter.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public class Shooter extends SubsystemBase {
  private final ShooterIO io;

  public Shooter(ShooterIO io) {
    this.io = io;
  }

  public Command runVoltage(DoubleSupplier volts) {
  }
}
