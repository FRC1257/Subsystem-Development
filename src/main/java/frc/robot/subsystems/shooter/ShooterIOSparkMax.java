package frc.robot.subsystems.shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.subsystems.shooter.ShooterConstants.HardwareConstants;

public class ShooterIOSparkMax implements ShooterIO {

  private final SparkFlex leftMotor;
  private final SparkFlex rightMotor;

  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

  private final SparkClosedLoopController leftController;
  private final SparkClosedLoopController rightController;

  private final SimpleMotorFeedforward leftFeedforward;
  private final SimpleMotorFeedforward rightFeedforward;

  public ShooterIOSparkMax() {
    leftMotor = new SparkFlex(HardwareConstants.LEFT_MOTOR_ID, MotorType.kBrushless);
    rightMotor = new SparkFlex(HardwareConstants.RIGHT_MOTOR_ID, MotorType.kBrushless);

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();

    SparkFlexConfig leftConfig = new SparkFlexConfig();
    SparkFlexConfig rightConfig = new SparkFlexConfig();

    leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotor.configure(
        rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    leftController = leftMotor.getClosedLoopController();
    rightController = rightMotor.getClosedLoopController();

    leftFeedforward = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
    rightFeedforward = new SimpleMotorFeedforward(0.0, 0.0, 0.0);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    inputs.leftFlywheelPositionRotations = Units.Rotations.of(leftEncoder.getPosition());
    inputs.leftFlywheelVelocity = Units.RPM.of(leftEncoder.getVelocity());
    inputs.leftFlywheelAppliedVolts =
        Units.Volts.of(leftMotor.getAppliedOutput() * leftMotor.getBusVoltage());
    inputs.leftFlywheelOutputCurrent = Units.Amps.of(leftMotor.getOutputCurrent());

    inputs.rightFlywheelPositionRotations = Units.Rotations.of(rightEncoder.getPosition());
    inputs.rightFlywheelVelocity = Units.RPM.of(rightEncoder.getVelocity());
    inputs.rightFlywheelAppliedVolts =
        Units.Volts.of(rightMotor.getAppliedOutput() * rightMotor.getBusVoltage());
    inputs.rightFlywheelOutputCurrent = Units.Amps.of(rightMotor.getOutputCurrent());
  }

  @Override
  public void setRPM(AngularVelocity leftRPM, AngularVelocity rightRPM) {
    double leftSetpoint = leftRPM.in(Units.RPM);
    double rightSetpoint = rightRPM.in(Units.RPM);

    double leftFeedforwardVolts = leftFeedforward.calculate(leftRPM.in(Units.RadiansPerSecond));
    double rightFeedforwardVolts = rightFeedforward.calculate(rightRPM.in(Units.RadiansPerSecond));

    leftController.setReference(
        leftSetpoint, ControlType.kVelocity, ClosedLoopSlot.kSlot0, leftFeedforwardVolts);
    rightController.setReference(
        rightSetpoint, ControlType.kVelocity, ClosedLoopSlot.kSlot0, rightFeedforwardVolts);
  }

  @Override
  public void setVoltage(Voltage leftVolts, Voltage rightVolts) {
    leftMotor.setVoltage(leftVolts.in(Units.Volts));
    rightMotor.setVoltage(rightVolts.in(Units.Volts));
  }

  @Override
  public void stop() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }
}
