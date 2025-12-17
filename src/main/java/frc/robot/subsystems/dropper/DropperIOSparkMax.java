package frc.robot.subsystems.dropper;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.servohub.ServoHub.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;

public class DropperIOSparkMax implements DropperIO {
    
    private ProfiledPIDController controller = new ProfiledPIDController(0, 0, 0, new TrapezoidProfile(-12, 12));
    private SparkFlex rightMotor;
    private RelativeEncoder rightEncoder;    
    private SparkFlex leftMotor;
    private RelativeEncoder leftEncoder;

    public DropperIOSparkMax() {
        //motors
        leftMotor = new SparkFlex(DropperConstants.blank, MotorType.kBrushless);//make a constant for motor id and ask the type of motor
        rightMotor = new SparkFlex(DropperConstants.blankmotorid, MotorType.kBrushless);

        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        SparkFlexConfig configLeft = new SparkFlexConfig();
        configLeft
            .idleMode(IdleMode.kBrake)
            .voltageCompensation(12)
            .smartCurrentLimit(NEO_CURRENT_LIMIT)
            .inverted(true);
        
        leftMotor.configure(configLeft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkFlexConfig configRight = new SparkFlexConfig();
        configRight
            .idleMode(IdleMode.kBrake)
            .voltageCompensation(12)
            .smartCurrentLimit(NEO_CURRENT_LIMIT)
            .inverted(false);
        rightMotor.configure(configLeft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);   
    }
    //methods from IO
    
    @Override
    
    public void updateInputs(DropperIOInputs inputs){
        //do later im lazy
    }
    
    @Override //not sure if there is one of these for each motor or not
    
    public double getAngle(){
        return leftEncoder.getPosition() * 6.28; //its just two pi make a pi constant later
    }
    
    @Override
    
    public double getAngVelocity(){
        double rpm = leftEncoder.getVelocity();//in rotations per min i think so convert
        double rps = rpm * 6.28 / 60; //radians per second (its just 2pi divide by 60)
        return rps;
    }

    @Override
    public void setVoltage(double voltage) {
        leftMotor.setVoltage(voltage);//???
        rightMotor.setVoltage(voltage);//idk about this
      }
    
    @Override

    public void setPosition(){

    }

    @Override

    public void stop(){
        leftMotor.stopMotor();
        rightMotor.stopMotor();
    }

    //PID

    public void setPIDGains(double kp, ki, kd){
        velocityPID.setP(p);//idek
        velocityPID.setI(i);
        velocityPID.setD(d);
    }



}

/*
    public default void setVoltage(double voltage) {}
    
    public default void setPosition(int position) {}
    
    public default void stop() {}

    // PID
    public default void setPIDGains(double kp, ki, kd) {}
    public default double getP() {}
    public default double getI() {}
    public default double getD() {}
 */