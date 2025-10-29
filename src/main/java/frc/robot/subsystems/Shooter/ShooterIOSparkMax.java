package frc.robot.subsystems.Shooter;


import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkFlex;

public class ShooterIOSparkMax implements ShooterIO {
    private SparkClosedLoopController leftController; //velocity pid controller (left)
    private SparkClosedLoopController rightController; //velocity pid controller (right)
    private SimpleMotorFeedforward leftFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
    private SimpleMotorFeedforward rightFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
    public PWMSparkFlex leftMotor;
    public PWMSparkFlex rightMotor;

    @Override
    public void setVoltage(double voltage) {
        leftMotor.setVoltage(voltage);
        rightMotor.setVoltage(voltage);
    }



    @Override
    public void setPIDGains(double kP, double kI, double kD){
        leftController.setP(kP);
        leftController.setI(kI);
        leftController.setD(kD);
        rightController.setP(kP);
        rightController.setI(kI);
        rightController.setD(kD);
        //where are the set methods supposed to be coming from?
        
    }

    @Override
    public void setRPM(double rpm){
        leftController.setReference(rpm, com.revrobotics.ControlType.kVelocity, 0, leftFF);
        rightController.setReference(rpm, com.revrobotics.ControlType.kVelocity, 0, rightFF);
        //is this correct?
    }

    @Override
    public void setFeedForwardGains(double kS, double kV, double kA) {
        leftFF = new SimpleMotorFeedforward(kS, kV, kA);
        rightFF = new SimpleMotorFeedforward(kS, kV, kA);
    }



}
