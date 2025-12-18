package frc.robot.subsystems.dropper;

public interface DropperIO {

    public static class DropperIOInputs {
        public double angle;
        public double velocity;
        public double appliedVoltage;
        public double angleRads;
        public double angVelocityRadsPerSec;
    }
    
    // Methods to be implemented in IOSim and IOSparkMax
    public default void updateInputs(DropperIOInputs inputs) {}

    public default double getAngle() {
        return 0.0;
    }

    public default double getAngVelocity() {
        return 0.0;
    }

    public default void setVoltage(double voltage) {}
    
    public default void setPosition(int position) {}

    public default void setSetpoint(double setpoint) {}

    public default void goToSetpoint(double setpoint) {}
    
    public default void stop() {}

    // PID
    public default void setPIDGains(double kp, double ki, double kd) {}
    public default double getP() {
        return 0.0;
    }
    public default double getI() {
        return 0.0;
    }
    public default double getD() {
        return 0.0;
    }
}
