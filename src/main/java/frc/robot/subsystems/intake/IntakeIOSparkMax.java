package frc.robot.subsystems.intake;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import org.littletonrobotics.junction.Logger;

public class IntakeIOSparkMax implements IntakeIO{
    private SparkFlex frontMotor;
  private SparkFlex backMotor;
  private RelativeEncoder encoder;

  private ProfiledPIDController pidController = new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(MAX_VELOCITY,MAX_ACCERLERATION));

  public IntakeIOSparkMax() {
    frontMotor = new SparkFlex(IntakeConstants.FRONT_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);
    backMotor = new SparkFlex(IntakeConstants.BACK_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);

    encoder = frontMotor.getEncoder();

    SparkFlexConfig frontConfig = new SparkFlexConfig();

    frontConfig 
        .idleMode(SparkBaseConfig.IdleMode.kBrake)
        .voltageCompensation(12)
        .smartCurrentLimit(NEO_CURRENT_LIMIT);

    SparkFlexConfig backConfig = new SparkFlexConfig();

    backConfig.apply(frontConfig);
    backConfig.follow(frontMotor);

    frontMotor.configure(frontConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
    backMotor.configure(backConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    inputs.velocityRPM = getRPM();
    inputs.appliedVoltage = frontMotor.getAppliedOutput() * frontMotor.getBusVoltage(); 
    inputs.motorCurrent =frontMotor.getOutputCurrent(); 
  }

  @Override
  public double getRPM() {
    return encoder.getVelocity();
  }

  @Override
  public void setPIDGains(double Kp, double Ki, double Kd) {
    pidController.setPID(Kp, Ki, Kd);
  }

  @Override
  public void setVoltage(double voltage) {
    frontMotor.setVoltage(voltage);
    Logger.recordOutput("Shooter/Set Voltage", voltage); 
  }

  @Override
  public double getVoltage() {
    return frontMotor.getAppliedOutput() * frontMotor.getBusVoltage(); 
  }

  @Override
  public void setRPM(double rpm) {
    double voltage = pidController.calculate(getRPM(), rpm); 
    frontMotor.setVoltage(voltage);
  }
  @Override
  public void setDirection(boolean forward){
    frontMotor.setInverted(forward);
  }
}

