package main.java.frc.robot.subsystems.shooter;

public interface ShooterIO{
        default void setVoltage(double voltage){}
        default double getVoltage(){
            return 0;
        }
        
        default void setPIDGains(double Kp, double Ki, double Kd){}
        
        default void setRPM(double rpm){}
        default void setFeedforwardGains(double Ks, double Kv, double Ka){}

    }
