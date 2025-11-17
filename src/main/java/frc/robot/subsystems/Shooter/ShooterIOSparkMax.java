package frc.robot.subsystems.Shooter;
import edu.wpi.first.math.controller.ProfiledPIDController;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.littletonrobotics.junction.AutoLog;
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

        private ProfiledPIDController pidController = new ProfiledPIDController(
        ShooterConstants.kP,
        ShooterConstants.kI,
        ShooterConstants.kD,
        new TrapezoidProfile.Constraints(
            ShooterConstants.MAX_VELOCITY,
            ShooterConstants.MAX_ACCELERATION
        )
    );

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
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
    }

    @Override
    public void setRPM(double rpm){
        SparkBase.ControlType controlType = SparkBase.ControlType.kVelocity;
        leftController.setReference(rpm, controlType);
        rightController.setReference(rpm, controlType);
    }






   

}
