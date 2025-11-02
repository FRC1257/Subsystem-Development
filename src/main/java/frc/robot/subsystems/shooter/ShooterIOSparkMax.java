package frc.robot.subsystems.shooter;

import static frc.robot.Constants.ElectricalLayout.SHOOTER_LEFT_ID;
import static frc.robot.Constants.ElectricalLayout.SHOOTER_RIGHT_ID;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import org.littletonrobotics.junction.Logger;
import com.revrobotics.spark.SparkLowLevel;


public class ShooterIOSparkMax implements ShooterIO {

  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  public SparkFlex leftMotor;
  public SparkFlex rightMotor;

  private SparkClosedLoopController leftController;
  private SparkClosedLoopController rightController;

  private SimpleMotorFeedforward leftFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
  private SimpleMotorFeedforward rightFF = new SimpleMotorFeedforward(0.0, 0.0, 0.0);

  SparkFlexConfig config;

  public ShooterIOSparkMax() {
    leftMotor = new SparkFlex(SHOOTER_LEFT_ID, MotorType.kBrushless);
    rightMotor = new SparkFlex(SHOOTER_RIGHT_ID, MotorType.kBrushless);

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();

    rightMotor.setInverted(true); // says this is deprecated

    leftController = leftMotor.getClosedLoopController();
    rightController = rightMotor.getClosedLoopController();

    config = new SparkFlexConfig();
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    inputs.leftShooterPositionRotations = leftEncoder.getPosition();
    inputs.leftFlywheelVelocityRPM = leftEncoder.getVelocity();
    inputs.leftFlywheelAppliedVolts = leftMotor.getAppliedOutput() * 12;
    inputs.leftFlywheelOutputCurrent = leftMotor.getOutputCurrent();

    inputs.rightFlywheelPositionRotations = rightEncoder.getPosition();
    inputs.rightFlywheelVelocityRPM = rightEncoder.getVelocity();
    inputs.rightFlywheelAppliedVolts = rightMotor.getAppliedOutput() * 12;
    inputs.rightFlywheelOutputCurrent = rightMotor.getOutputCurrent();
    // 2024 code
  }

  @Override
  public void setVoltage(double leftVoltage, double rightVoltage) {
    leftMotor.setVoltage(leftVoltage);
    rightMotor.setVoltage(rightVoltage);
  }

  @Override
  public void setPIDGains(double kP, double kI, double kD) {
    config.closedLoop.p(kP).i(kI).d(kD); //idk how to do this I looked at rev documentation
    
  }

  @Override
  public void setRPM(double leftRpm, double rightRpm) {
    leftController.setReference(leftRpm, ControlType.kVelocity);
    rightController.setReference(rightRpm, ControlType.kVelocity);
  }

  @Override
  public void setFeedForwardGains(double kS, double kV, double kA) {
    leftFF = new SimpleMotorFeedforward(kS, kV, kA);
    rightFF = new SimpleMotorFeedforward(kS, kV, kA);
  }
}
