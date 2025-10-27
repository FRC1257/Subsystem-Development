package frc.robot.subsystems.Shooter;

public interface ShooterIO {
    default void setVoltage(double voltage) {}
    default double getVoltage() {
        return 0.0;
    }
    default void setPIDGains(double kP, double kI, double kD) {}
    default void setRPM(double rpm){}
    default void setFeedForwardGains(double kS, double kV, double kA) {}

    public default void setP(double p) {}
    
    public default void setI(double i) {}

    public default void setD(double d) {}

}
