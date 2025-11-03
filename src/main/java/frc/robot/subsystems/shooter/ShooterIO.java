package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterIO {

    default double getVoltage() { return 0.0; }
    
    public default void setVoltage(double voltage) {} 
    
    public default void setPIDGains(double Kp, double Ki, double Kd)

    public default void setRPM(double rpm) {}

    public default void setFeedforwardGains(double Ks, double Kv, double Ka) [IO Files]


}
