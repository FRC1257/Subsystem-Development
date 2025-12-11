package frc.robot.subsystems.claw;
import edu.wpi.firstwpilibj2.command.SubsystemBase;

public class claw extends SubsystemBase {
	private final ClawIO io;
private ClawIOInputsAutoLogged inputs = new ClawIOInputsAutoLogged();

public Claw(ClawIO io) {
		this.io = io;
	}
	@Overide
	public void period() {
		io.updateInputs(inputs);
		Logger.processInputs(key:“Claw”, inputs);
	}
    public Command openClaw() {
        return runEnd(
            () -> io.open()
        )
    }
    public Command closeClaw() {
        return runEnd(
            () -> io.close()
        )
    }
    public Command stopCommand() {
        return runOnce(
            () -> io.stop()
        )
    }
}