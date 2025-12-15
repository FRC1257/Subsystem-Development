package frc.robot.subsystems.dropper;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.revrobotics.spark.config.SparkFlexConfig;

public class DropperIOSparkMax implements DropperIO {
    
    private ProfiledPIDController controller = new ProfiledPIDController(0, 0, 0, new TrapezoidProfile(-12, 12));
    private SparkFlex motor;
    private RelativeEncoder encoder;

    public DropperIOSparkMax() {
        leftmotor = new SparkFlex(DropperConstants.blank, MotorType.kBrushless);//make a constant for motor id and ask the type of motor
        rightmotor = new SparkFlex(DropperConstants.blankmotorid, MotorType.kbrushless);

        leftEncoder = leftMotor.getEncoder();
        rightEncoder = rightMotor.getEncoder();

        SparkFlexConfig config = new SparkFlexConfig();
        config
            .idleMode(IdleMode.kbrake)
            .voltageCompensation(12)
            .smartCurrentLimit(NEO_CURRENT_LIMIT)
            .inverted(true);
        
        leftMo


        
    }



}