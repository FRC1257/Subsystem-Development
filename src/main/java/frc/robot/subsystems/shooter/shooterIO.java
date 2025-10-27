package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface shooterIO {
    @AutoLog
    public static class shooterIntakeIOInputs {
        public double appliedVoltage = 0.0;
        public double currentRPM = 0.0;

    }
    public default void setVoltage(double voltage){}
    
    public default double getVoltage() {
        return 0.0;
    }

    public default void setRPM(double rpm){}

    public default void setPIDGains(double Kp, double Ki, double Kd){}
}
