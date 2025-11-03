package frc.robot.subsystems.shooter;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;

public ShooterIOSparkMax() {
    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();

    leftController = leftMotor.getClosedLoopController();
    rightController = rightMotor.getClosedLoopController();


}

@Override 
public double getVoltage() {
    return (leftMotor.getAppliedOutput() + rightMotor.getAppliedOutput()) / 2.0;
}

@Override
public void setFeedForwardGains(double kS, double kV, double kA) {
    leftFF = new SimpleMotorFeedforward(kS, kV, kA);
    rightFF = new SimpleMotorFeedforward(kS, kV, kA);
}

@Override
public void setVoltage(double voltage) {
    leftMotor.setVoltage(voltage);
    rightMotor.setVoltage(voltage);
}
@Override
public void setPIDGains(double kP, double kI, double kD) {
    ClosedLoopConfig PIDconfig = new ClosedLoopConfig();
    PIDconfig.pid(kP, kI, kD);
   
}

@Override
public void setRPM(double rpm){
    SparkBase.ControlType controlType = SparkBase.ControlType.kVelocity;
    leftController.setReference(rpm, controlType);
    rightController.setReference(rpm, controlType);
}