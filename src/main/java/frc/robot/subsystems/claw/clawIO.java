package frc.robot.subsystem.claw;
import org.littletonrobotics.junction.Autolog;

public interface ClawIO {
    @AutoLog
    public class ClawIOInputs {
    }
    public default void updateInputs(ClawIOInputs inputs) {}
	public default void setVoltage(double volts) {}
    public default void open(double volts) {}
    public default void close(double volts) {}
    public default void stop() {}
}