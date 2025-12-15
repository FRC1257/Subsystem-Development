package frc.robot.subsystems.dropper;

public interface DropperIO {

    public class ArmIOInputs {
        public double angle;
        public double velocity;
        public double appliedVoltage;
        public double angleRads;
        public double angVelocityRadsPerSec;
    }
    
    public default void updateInputs(ArmIOInputs inputs) {}

    public default double getAngle() {}

    public default double getAngVelocity() {}

    public default void setVoltage(double voltage) {}
    
    public default void setPosition(int position) {}
    
    public default void stop() {}
}
