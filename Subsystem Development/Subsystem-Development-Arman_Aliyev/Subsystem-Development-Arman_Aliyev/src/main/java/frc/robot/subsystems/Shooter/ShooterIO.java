package frc.robot.subsystems.Shooter;
import org.littletonrobotics.junction.AutoLog;
@AutoLog

public interface ShooterIO {
    public static class shooterIOIn{
public static double frontTempC = 0.0;
public static double frontVelocityRPM = 0.0;
public static double frontCurrentAmps = 0.0;
public static double frontAppVolts = 0.0;

public static double rearTempC = 0.0;
public static double rearVelocityRPM = 0.0;
public static double rearCurrentAmps = 0.0;
public static double rearAppVolts = 0.0;
    
public void setFrontVoltage(double voltage){
}
public void setRearVoltage(double voltage){
} // im not sure where to put this bc this is the simple part of setting volatge
// but supposedly there is a more complex implementation that goes into shooter
public double getFrontVelocity() {
    return 0;
  }
public double getRearVelocity() {
    return 0;
  }
}
    public default void updateInputs(shooterIOIn inputs) {}

    default void setBrakeMode(boolean enable){};

    default void stop() {}

    //default void configurePID(double kP, double kI, double kD) {}
    
    default void setPIDGains(double Kp, double Ki, double kD){}
    default void setFeedforwardGains(double Ks, double Kv, double Ka) {}
}
