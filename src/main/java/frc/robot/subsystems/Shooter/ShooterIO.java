package frc.robot.subsystems.shooter;

public interface ShooterIO {

     default double void setRPM(double rpm){}

     default void setVoltage(double voltage) {}

    default void setFeedforwardGains(double Ks, double Kv, double Ka){}

    default void setPIDGains(double Kp, double Ki, double Kd){}    
    
  
}
