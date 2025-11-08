package frc.robot.subsystems.Shooter;
import org.littletonrobotics.junction.AutoLog;
@AutoLog

//needed methods : setvoltage 

public interface ShooterIO { //define methods but not implementations in interface
    public static class shooterIOIn{
    

public double getVoltage(){
  return 0;
}
public void setVoltage(double voltage){} 


public double getVelocity() {
    return 0;
  }
public void setVelocity(double Velocity){}


public double getRPM(double rpm){
  return 0;
}
public void setRPM(double rpm){}


}
    public default void updateInputs(shooterIOIn inputs) {}

    //default void setBrakeMode(boolean enable){};

    //default void stop() {}

    //default void configurePID(double kP, double kI, double kD) {}
    
    default void setPIDGains(double Kp, double Ki, double kD){}
    default void setFeedforwardGains(double Ks, double Kv, double Ka) {}
}
