package frc.robot.subsystems.dropper;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

    public command stop(){
        return runOnce(() -> io.stop().withName("Dropper Stop"));
    }
    



}
