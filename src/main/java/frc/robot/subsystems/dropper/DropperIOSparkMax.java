package frc.robot.subsystems.dropper;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;

public class DropperIOSparkMax implements DropperIO {
    
    private ProfiledPIDController controller = new ProfiledPIDController(0, 0, 0, new TrapezoidProfile(-12, 12));
    private SparkMax rightMotor; //changed from spark flex to can spark max cuz 
    private RelativeEncoder rightEncoder;    
    private SparkMax leftMotor;
    private RelativeEncoder leftEncoder;
    private final ProfiledPIDController pidController;
    private double pi = DropperConstants.PI;

    public DropperIOSparkMax() {
        //motors
        leftMotor = new SparkMax(DropperConstants.LEFT_DROPPER_MOTOR_ID, MotorType.kBrushless);//ask the type of motor
        rightMotor = new SparkMax(DropperConstants.RIGHT_DROPPER_MOTOR_ID, MotorType.kBrushless);
        
        rightMotor.follow(leftMotor, false);

        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        SparkMaxConfig configLeft = new SparkMaxConfig();
        configLeft
            .idleMode(IdleMode.kBrake)
            .voltageCompensation(12)
            .smartCurrentLimit(NEO_CURRENT_LIMIT)
            .inverted(true);
        
        leftMotor.configure(configLeft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);   
    }
    //methods from IO
    
    @Override
    
    public void updateInputs(DropperIOInputs inputs){ //update the imputs form io
        inputs.appliedVoltage = leftMotor.getAppliedOutput() * leftMotor.getBusVoltage();
        inputs.angVelocityRadsPerSec = leftEncoder.getVelocity() * 2 * pi / 60; // in rotations so radians per sec converted
        inputs.angleRads = leftEncoder.getPosition() * 2 * pi;
        inputs.angle = leftEncoder.getPosition() * 360;
        inputs.velocity = leftEncoder.getVelocity();
    }
    
    @Override //not sure if there is one of these for each motor or not
    
    public double getAngle(){
        return leftEncoder.getPosition() * 2 * pi; //its just gets the rotations and convers to angle using two pi make a pi constant later
    }
    
    @Override
    
    public double getAngVelocity(){
        double rpm = leftEncoder.getVelocity();//in rotations per min i think so convert
        double rps = rpm * 2 * pi / 60; //radians per second (its just 2pi divide by 60)
        return rps;
    }

    @Override
    public void setVoltage(double voltage) {
        leftMotor.setVoltage(voltage);//???
        rightMotor.setVoltage(voltage);//idk about this
      }
    
    @Override

    public void setPosition(int position){
        leftEncoder.setPosition(position);
    }
    
    @Override
    public void setSetpoint(double setpoint){
        pidController.setGoal(setpoint);
        pidController.reset(getAngle(), getAngVelocity());

    }

    @Override
    public void goToSetpoint(){

    }

    @Override

    public void stop(){
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    //PID
    
    @Override
    public void setPIDGains(double kp, ki, kd){
        velocityPID.setP(kp);//idek
        velocityPID.setI(ki);
        velocityPID.setD(kd); 
    }

    @Override
    public default double getP(){
        return pidController.getP();
    }
    @Override
    public default double getI(){
        return pidController.getI();
    }
    @Override
    public default double getD(){
        return pidController.getD();
    }
}

    public default void setVoltage(double voltage) {}
    
    public default void setPosition(int position) {}
    
    public default void stop() {}
/*
    // PID
    public default void setPIDGains(double kp, ki, kd) {}
    public default double getP() {}
    public default double getI() {}
    public default double getD() {}
 */