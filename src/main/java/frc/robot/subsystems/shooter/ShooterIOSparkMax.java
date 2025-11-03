package frc.robot.subsystems.shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class ShooterIOSparkMax implements ShooterIO{
    private SparkFlex upperMotor = new SparkFlex(ShooterConstants.UPPER_MOTOR_ID, MotorType.kBrushless);
    private SparkFlex lowerMotor = new SparkFlex(ShooterConstants.LOWER_MOTOR_ID, MotorType.kBrushless);

    private final RelativeEncoder upperEncoder = upperMotor.getEncoder();
    private final RelativeEncoder lowerEncoder = lowerMotor.getEncoder();

    //PID and FeedForward controllers
    private SparkClosedLoopController upperController = upperMotor.getClosedLoopController();
    private SparkClosedLoopController lowerController = lowerMotor.getClosedLoopController();
    private SimpleMotorFeedforward upperFF = new SimpleMotorFeedforward(0.0,0.0,0.0);
    private SimpleMotorFeedforward lowerFF = new SimpleMotorFeedforward(0.0,0.0,0.0);

    @Override
    public void updateInputs(ShooterIOInputs inputs){
        
    }

    @Override
    public void setVoltage(double voltage){
        upperMotor.setVoltage(voltage);
        lowerMotor.setVoltage(voltage);
    }

    @Override
    public double getVoltage(){
        //I didn't know exactly how to do this so copilot helped, idk if you wanted seperate voltages
    double upperMotorVoltage = upperMotor.getAppliedOutput() * upperMotor.getBusVoltage();
    double lowerMotorVoltage = lowerMotor.getAppliedOutput() * lowerMotor.getBusVoltage();
    return (upperMotorVoltage + lowerMotorVoltage) / 2.0;
    }
    
    @Override
    public void setPIDGains(double Kp, double Ki, double Kd){
        upperController.setP(Kp);
        upperController.setI(Ki);
        upperController.setD(Kd);

        lowerController.setP(Kp);
        lowerController.setI(Ki);
        lowerController.setD(Kd);
    }

    @Override
    public void setRPM(double rpm){
        upperController.setReference(rpm, com.revrobotics.spark.SparkClosedLoopController.ControlType.kVelocity, 0, upperFF.calculate(rpm/60.0*2.0*Math.PI));
        lowerController.setReference(rpm, com.revrobotics.spark.SparkClosedLoopController.ControlType.kVelocity, 0, lowerFF.calculate(rpm/60.0*2.0*Math.PI));
    }

    @Override
    public void setFeedforwardGains(double Ks, double Kv, double Ka){
        upperFF = new SimpleMotorFeedforward(Ks, Kv, Ka);
        lowerFF = new SimpleMotorFeedforward(Ks, Kv, Ka);
    }

    @Override
    public void stop(){
        upperMotor.stopMotor();
        lowerMotor.stopMotor();
    }
}

