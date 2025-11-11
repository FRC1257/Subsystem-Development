package frc.robot.subsystems.PersonalArm;

import java.util.Set;

import org.littletonrobotics.junction.AutoLog;


//MADE BASED OFF OF CORAL PIVOT

public interface PersonalArmIO {
  @AutoLog // creates the class for us
  public class PersonalArmIOInputs {
    public double angle;
    public double velocity;
    public double appliedVoltage;
  }

  public default void updateInputs(PersonalArmIOInputs inputs) {}

  public default void setVoltage(double motorVolts) {}
  //needa figure out how these work
  public default double getAngle() {
    return 0.0;
  } 
  public default double getAngVelocity() {
    return 0.0;
  } 
  public default void setSetpoint(double setpoint) {}
  public default void goToSetpoint() {}
  public default void setBrake(boolean brake) {}
  public default boolean atSetpoint() {
    return false;
  }


  //setters for PID and feedforward
  public default void setP(double p) {}
  public default void setI(double i) {}
  public default void setD(double d) {}
  public default void setkS(double kS) {}
  public default void setkG(double kG) {}
  public default void setkV(double kV) {}
  public default void setkA(double kA) {}

  //getters for PID and feedforward
  public default double getP(){
    return 0.0;
  }

  public default double getI(){
    return 0.0;
  }

  public default double getD(){
    return 0.0;
  }

  public default double getkS(){
    return 0.0;
  }

  public default double getkG(){
    return 0.0;
  }

  public default double getkV(){
    return 0.0;
  }

  public default double getKa(){
    return 0.0;
  }









}
