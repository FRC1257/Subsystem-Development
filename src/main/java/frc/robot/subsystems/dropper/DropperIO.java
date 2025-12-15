package frc.robot.subsystems.dropper;

public interface DropperIO {

    public class DropperIOInputs {
        public double angle;
        public double velocity;
        public double appliedVoltage;
        public double angleRads;
        public double angVelocityRadsPerSec;
    }
    
    // Methods to be implemented in IOSim and IOSparkMax
    public default void updateInputs(DropperIOInputs inputs) {}

    public default double getAngle() {
        return DropperIOInputs.angle;
    }

    public default double getAngVelocity() {
        return DropperIOInputs.velocity;
    }

    public default void setVoltage(double voltage) {}
    
    public default void setPosition(int position) {}
    
    public default void stop() {}

    // PID
    public default void setPIDGains(double kp, ki, kd) {}
    public default double getP() {}
    public default double getI() {}
    public default double getD() {}
}
