package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
    @AutoLog
    public static class ShooterIOInputs {
        public double leftRPM = 0.0;
        public double rightRPM = 0.0;
        public double leftVoltage = 0.0;
        public double rightVoltage = 0.0;
        public double leftCurrent = 0.0;
        public double rightCurrent = 0.0;
    }

    default void updateInputs(ShooterIOInputs inputs) {}

    default void setVoltage(double leftVolts, double rightVolts) {}

    default void setVelocity(double leftRPM, double rightRPM) {}

    default double getLeftRPM() { 
        return 0.0; 
    }
    default double getRightRPM() { 
        return 0.0; 
    }

    default double getLeftVoltage() { 
        return 0.0; 
    }
    default double getRightVoltage() { 
        return 0.0; 
    }

    public default void setP(double p) {}
    public default void setI(double i) {}
    public default void setD(double d) {}
    public default void setkS(double kS) {}
    public default void setkV(double kV) {}
    public default void setkG(double kG) {}
    public default void setkA(double kA) {}
    public default void setActiveP(double p) {}
    public default void setActiveI(double i) {}
    public default void setActiveD(double d) {}
    public default void setActivekS(double kS) {}
    public default void setActivekV(double kV) {}
    public default void setActivekG(double kG) {}
    public default void setActivekA(double kA) {}
    public default double getP() {
        return 0.0;
    }
    public default double getI() {
        return 0.0;
    }
    public default double getD() {
        return 0.0;
    }
    public default double getkS() {
        return 0.0;
    }
    public default double getkG() {
        return 0.0;
    }
    public default double getkV() {
        return 0.0;
    }
    public default double getkA() {
        return 0.0;
    }
    public default double getActiveP() {
        return 0.0;
    }
    public default double getActiveI() {
        return 0.0;
    }
    public default double getActiveD() {
        return 0.0;
    }
    public default double getActivekS() {
        return 0.0;
    }
    public default double getActivekG() {
        return 0.0;
    }
    public default double getActivekV() {
        return 0.0;
    }
    public default double getActivekA() {
        return 0.0;
    }
}
