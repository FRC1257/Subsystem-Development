package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;



public interface IntakeIO {
    public static class IntakeIOInputs {
    public double velocityRadsPerSec = 0.0;

    public double appliedVoltage = 0.0;
    public double[] currentAmps = new double[] {};
    public double[] tempCelcius = new double[] {};
  }

  public default void setBrake(boolean brake) {}

  public default void updateInputs(IntakeIOInputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default double getVoltage() {
    return 0;
  }

  public default void setDirection(boolean forward){}
  
  public default double getRPM(){
    return 0;
  }

  public default void setRPM(double rpm){}
  
  public default void setPIDGains(double Kp, double Ki, double Kd) {}
}
