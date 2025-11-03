package main.java.frc.robot.subsystems.shooter;

//IO
//Empty methods

//void setRPM(double rpm) [IO Files]
//void setVoltage(double voltage) [IO Files]

//void setFeedforwardGains(double Ks, double Kv, double Ka) [IO Files]
//void setPIDGains(double Kp, double Ki, double Kd) [IO Files]

public interface ShooterIO {
  class ShooterIOInputs {}

  default void setRPM(double rpm) {}
  default void setVoltage(double voltage) {}

  default void setPIDGains(double Kp, double Ki, double Kd){}
  default void setFeedFOrwardGains(double Ks, double Kv, double Ka) {}
}

