package frc.robot.subsystems.shooter;

import static frc.robot.Constants.NEO_CURRENT_LIMIT;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.math.controller.PIDController;
import org.littletonrobotics.junction.Logger;

public class ShooterIOSparkMax implements ShooterIO {
  private SparkFlex frontMotor;
  private SparkFlex backMotor;
  private RelativeEncoder encoder;

  private PIDController pidController = new PIDController(0, 0, 0);

  public ShooterIOSparkMax() {
    frontMotor = new SparkFlex(ShooterConstants.FRONT_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);
    backMotor = new SparkFlex(ShooterConstants.BACK_MOTOR_ID, SparkLowLevel.MotorType.kBrushless);

    encoder = frontMotor.getEncoder();

    SparkFlexConfig frontConfig = new SparkFlexConfig();

    frontConfig // I just copied these from CoralIntakeIOSparkMax
        .idleMode(SparkBaseConfig.IdleMode.kBrake)
        .voltageCompensation(12)
        .smartCurrentLimit(NEO_CURRENT_LIMIT);

    SparkFlexConfig backConfig = new SparkFlexConfig();

    backConfig.apply(frontConfig);
    backConfig.follow(frontMotor);

    frontMotor.configure(
        frontConfig,
        SparkBase.ResetMode.kResetSafeParameters,
        SparkBase.PersistMode.kPersistParameters);
    backMotor.configure(
        backConfig,
        SparkBase.ResetMode.kResetSafeParameters,
        SparkBase.PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    inputs.velocityRPM = getRPM();
    inputs.appliedVoltage = frontMotor.getAppliedOutput() * frontMotor.getBusVoltage(); // what
    inputs.motorCurrent =
        frontMotor
            .getOutputCurrent(); // You told me that this shouldn't be an array, so I'm guessing
    // that both motors have the same.
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
    Logger.recordOutput(
        "Shooter/Set Voltage",
        voltage); // Took this from ElevatorIOSparkMax, hoping this is what I'm supposed to do
  }

  @Override
  public double getVoltage() {
    return frontMotor.getAppliedOutput()
        * frontMotor
            .getBusVoltage(); // idk maybe this is it idk what the difference between voltage and
    // applied voltage is
  }

  @Override
  public void setRPM(double rpm) {
    double voltage = pidController.calculate(getRPM(), rpm);

    frontMotor.setVoltage(voltage);
  }
}
