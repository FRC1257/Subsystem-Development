package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO{
        @AutoLog
        public static class ShooterIOInputs{
            public double voltage = 0.0;
        }
        default void updateInputs(ShooterIOInputs inputs){}
    
        default void setVoltage(double voltage){}

        default double getVoltage(){
            return 0.0;
        }
        
        default void setPIDGains(double Kp, double Ki, double Kd){}

        default void setRPM(double rpm){}

        default void setFeedforwardGains(double Ks, double Kv, double Ka){}

        default void stop(){}
    }
