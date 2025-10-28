package frc.robot.subsystems.shooter;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import frc.robot.subsystems.shooter.ShooterConstants.HardwareConstants;

public class ShooterIOSparkMax implements ShooterIO {

  private SparkFlex topMotor;
  private SparkFlex bottomMotor;

  private RelativeEncoder encoder;

  private PIDController feedbackController;
  private SimpleMotorFeedforward feedForwardController = new SimpleMotorFeedforward(0.0, 0.0, 0.0);

  public ShooterIOSparkMax() {
    topMotor = new SparkFlex(HardwareConstants.TOP_MOTOR_ID, MotorType.kBrushless);
    bottomMotor = new SparkFlex(HardwareConstants.BOTTOM_MOTOR_ID, MotorType.kBrushless);

    encoder = topMotor.getEncoder();

    topMotor.configure(
        new SparkFlexConfig()
            .smartCurrentLimit(0)
            .voltageCompensation(0.0)
            .idleMode(IdleMode.kBrake)
            .inverted(false)
            .apply(new EncoderConfig().velocityConversionFactor(0).positionConversionFactor(0)),
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }
}
