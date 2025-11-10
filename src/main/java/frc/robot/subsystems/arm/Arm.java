package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import java.util.function.DoubleSupplier;

import static frc.robot.subsystems.arm.ArmConstants.MAX_POSITION;
import static frc.robot.subsystems.arm.ArmConstants.MIN_POSITION;

public class Arm extends SubsystemBase {
    private final ArmIO io;
    private final ArmIOInputsAutoLogged inputs=new ArmIOInputsAutoLogged();

    public Arm(ArmIO io) {
       this.io = io;
    }

    //bro why is default indentation for this 2 spaces

    @Override
    public void periodic() {
      io.updateInputs(inputs);
      Logger.processInputs("Arm",inputs);
    }

    public Command runPosition(DoubleSupplier angle) {
      return runEnd(
              ()->io.setPosition(MathUtil.clamp(angle.getAsDouble(),MIN_POSITION,MAX_POSITION)),
              ()->io.setPosition(0)
      ).withName("Moving my arm");
    }

    public Command stop() {
        return runOnce(()->io.setPosition(0))
                .withName("OH NO I STOPPED MOVING MY ARM");
    }
}
