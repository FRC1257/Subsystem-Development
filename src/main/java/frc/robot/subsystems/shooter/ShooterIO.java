package frc.robot.subsystems.shooter;

public interface ShooterIO {

    public default void setVoltage(double voltage) {}

    public default double getVoltage() {
        return 0;
    }

    public default void setRPM(double rpm) {}

    public default void setPIDGains(double Kp, double Ki, double Kd) {}
}
