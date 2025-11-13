package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;

public class Arm extends SubsystemBase {

  private final ArmIO io;
  private ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

  public Arm(ArmIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Arm", inputs);
  }

  public Command runPosition(DoubleSupplier angle) {
    return run(() ->
            io.setPosition(
                MathUtil.clamp(
                    angle.getAsDouble(), ArmConstants.LOWER_LIMIT, ArmConstants.UPPER_LIMIT)))
        .withName("Arm/Position-Command");
  }

  public Command stop() {
    return runOnce(() -> io.stop()).withName("Arm/Stop");
  }
}
