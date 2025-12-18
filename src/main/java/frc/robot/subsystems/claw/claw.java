package frc.robot.subsystems.claw;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
//Idk how to do the auto importation and idk what to import


public class Claw extends SubsystemBase {
	private final ClawIO io;
    private final ClawIOInputsAutoLogged inputs = new ClawIOInputsAutoLogged();
    public Claw(ClawIO io) {
            this.io = io;
        }
        @Override
        public void periodic() {
            //updates sensor readings
            io.updateInputs(inputs);
            //logs the information to AdvantageKit
            Logger.processInputs(“Claw”, inputs);
        }
        //comand to open the claw, runs with opening voltage until interrupted
        public Command openClaw() {
            return run(
                () -> io.open(ClawConstants.Claw_Open_Voltage)
                .withName("Claw/Open");
            )
        }
        //command to close the claw, runs with closing voltage until it is interrupted
        public Command closeClaw() {
            return run(
                () -> io.close(ClawConstants.Claw_Close_Voltage)
                .withName("Claw/Close")
            )
        }
        //command to stop the claw, justs sets velocity to zero
        public Command stopCommand() {
            return runOnce(
                () -> io.stop()
                .withName("Claw/Stop")
            )
        }
}