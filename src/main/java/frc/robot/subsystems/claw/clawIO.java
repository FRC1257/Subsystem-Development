public interface clawIO {
    @AutoLog
    public class clawIOInputs {
    }
    public default void updateInputs(clawIOInputs inputs) {}
	public default void setVoltage(double volts) {}
	public default void setPosition(double angle) {}
}