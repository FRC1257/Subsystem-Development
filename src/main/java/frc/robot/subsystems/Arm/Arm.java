package frc.robot.subsystems.Arm;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.MathUtil;
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
    public Command runPosition(DoubleSupplier angle) {

        return run(
            () -> io.setPosition(MathUtil.clamp(angle.getAsDouble(), ArmConstants.LOWER_LIMIT, ArmConstants.UPPER_LIMIT)))
            .withName("Arm/Position - Command");

         //setposition of arm's to position DoubleSupplier
        //runEnd = two runnables
        //what it does while the command ends and what it should do when the command end
        // while running - set position to position
        //after that - set it to 0 when it is done/ not running
        //could have done this with a run command instead of runend

        //last year code - have these commands + other junk that isn't rlly necessary
        //look at what top teams are doing
        
        //prevent the arm from moving beyond its limit

    }

    public Command stop(){
        return runOnce(() -> io.stop()).withName("Arm/Stop - Command");
    }

}
