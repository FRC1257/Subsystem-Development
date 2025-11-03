package frc.robot.subsystems.Shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;




public class ShooterIOSparkMax implements ShooterIO {
    public void updateInputs(ShooterIOInputs inputs) {
        inputs.voltage = getVoltage();

    }
    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;

    SparkFlex leftMotor = new SparkFlex(1, MotorType.kBrushless);
    SparkFlex rightMotor = new SparkFlex(2, MotorType.kBrushless);

    private SparkClosedLoopController leftController; // pid controller (left)
    private SparkClosedLoopController rightController; // pid controller (right)

    private SimpleMotorFeedforward leftFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
    private SimpleMotorFeedforward rightFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);





    public ShooterIOSparkMax() {
        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        leftController = leftMotor.getClosedLoopController();
        rightController = rightMotor.getClosedLoopController();


    }

    @Override
    public double getVoltage(){
        return (leftMotor.getAppliedOutput() + rightMotor.getAppliedOutput()) / 2.0;
        //didn't know what would actually go here
        //js let vscode fill it out
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
        //switch to use profiledpidcontroller
        //will make periodic w/ logging easier too
    }

    @Override
    public void setRPM(double rpm){
        SparkBase.ControlType controlType = SparkBase.ControlType.kVelocity;
        leftController.setReference(rpm, controlType);
        rightController.setReference(rpm, controlType);
    }





   

}
