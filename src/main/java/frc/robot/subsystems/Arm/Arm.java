package frc.robot.subsystems.Arm;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final ArmIO io;
    private ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();
    public Arm(ArmIO io) {
        this.io = io;
    }

    @Override
    //comes from subsystem base
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Arm", inputs);
    }

    public Command setVoltage(double voltage) {
        return runOnce(() -> io.setVoltage(voltage));
    }
//doublesupplier = runnables that return doubles
    public Command runPosition(DoubleSupplier position) {
        return runEnd(
            () -> io.setPosition(position.getAsDouble()),
            () -> io.setPosition(0.0)
            ).withName("Arm/Position - Command");

    }

}
