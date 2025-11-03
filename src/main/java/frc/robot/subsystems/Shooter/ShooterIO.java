package frc.robot.subsystems.Shooter;

import org.littletonrobotics.junction.AutoLog;


public interface ShooterIO {

    @AutoLog
    class ShooterIOInputs {
        public double voltage = 0.0;
    }
    default void setVoltage(double voltage) {}
    default double getVoltage() {
        return 0.0;
    }
    default void updateInputs(ShooterIOInputs inputs) {}
    default void setPIDGains(double kP, double kI, double kD) {}
    default void setRPM(double rpm){}
    default void setFeedForwardGains(double kS, double kV, double kA) {}
    default void stop() {}





}
