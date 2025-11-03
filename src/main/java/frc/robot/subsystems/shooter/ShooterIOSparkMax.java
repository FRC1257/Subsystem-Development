package frc.robot.subsystems.shooter;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class ShooterIOSparkMax implements ShooterIO {
    private SparkMax shooterMotor;
    private SparkMaxConfig config;
    private ShooterFeedforward feedforward = new ArmFeedforward(0, 0, 0, 0);
    private ShooterFeedforward feedforwardActive = new ArmFeedforward(0, 0, 0, 0);
    private SparkAbsoluteEncoder motorEncoder;

    private double setpointRPM = 0;
    private double lastTime = Timer.getFPGATimestamp();
    private double lastVelocity = 0;

    private double kP = ShooterConstants.SHOOTER_PID_REAL[0],
        kI = ShooterConstants.SHOOTER_PID_REAL[1],
        kD = ShooterConstants.SHOOTER_PID_REAL[2];
    private double kActiveP = ShooterConstants.SHOOTER_PID_REAL_ACTIVE[0],
        kActiveI = ShooterConstants.SHOOTER_PID_REAL_ACTIVE[1],
        kActiveD = ShooterConstants.SHOOTER_PID_REAL_ACTIVE[2];

    public ShooterIOSparkMax() {
        shooterMotor = new SparkMax(ShooterConstants.SHOOTER_MASTER_ID, MotorType.kBrushless);

        config = new SparkMaxConfig();

        config
            .idleMode(IdleMode.kBrake)
            .voltageCompensation(12.0)
            .smartCurrentLimit(Constants.NEO_CURRENT_LIMIT)
            .inverted(true);

        motorEncoder = pivotMotor.getAbsoluteEncoder();

        config
            .absoluteEncoder
            .zeroCentered(true)
            .zeroOffset(ShooterConstants.SHOOTER_OFFSET)
            .positionConversionFactor(2 * Constants.PI)
            .velocityConversionFactor(2 * Constants.PI);
        
        
        pivotMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        configureFeedForward();
        configureFeedForwardActive();
    }

    private void configureFeedForward() {
        setkS(ShooterConstants.SHOOTER_FEEDFORWARD_REAL[0]);
        setkG(ShooterConstants.SHOOTER_FEEDFORWARD_REAL[1]);
        setkV(ShooterConstants.SHOOTER_FEEDFORWARD_REAL[2]);
        setkA(ShooterConstants.SHOOTER_FEEDFORWARD_REAL[3]);
    }
    private void configureFeedForwardActive() {
        setActivekS(ShooterConstants.SHOOTER_FEEDFORWARD_REAL_ACTIVE[0]);
        setActivekG(ShooterConstants.SHOOTER_FEEDFORWARD_REAL_ACTIVE[1]);
        setActivekV(ShooterConstants.SHOOTER_FEEDFORWARD_REAL_ACTIVE[2]);
        setActivekA(ShooterConstants.SHOOTER_FEEDFORWARD_REAL_ACTIVE[3]);
  }

    @Override
    public void updateInputs(ShooterIOInputs inputs) {
        inputs.angleRads = getAngle();
        inputs.angVelocityRadsPerSec = motorEncoder.getVelocity();
        inputs.appliedVolts = pivotMotor.getAppliedOutput() * pivotMotor.getBusVoltage();
        inputs.setpointAngleRads = pidController.getSetpoint().position;
    }

    @Override
    public void setVoltage(double volts) {
        Logger.recordOutput("Shooter/Desired Voltage", volts);
        shooterMotor.setVoltage(volts);
    }
    @Override
        public double getAngle() {
        return motorEncoder.getPosition();
    }

    @Override
    public double getAngVelocity() {
    return motorEncoder.getVelocity();
    }

    @Override
    public void setSetpoint(double rpm) {
    pidController.setGoal(rpm);
    pidController.reset(getAngle(), getAngVelocity());
    pidControllerActive.setGoal(rpm);
    pidControllerActive.reset(getAngle(), getAngVelocity());
    Logger.recordOutput("Shooter/Actual Setpoint", pidController.getSetpoint().position);
    }

  @Override
  public void goToSetpoint() {
    double pidOutput = 0, ffOutput = 0;

    if (isBreakBeamBroken()) {
      pidOutput = pidControllerActive.calculate(getAngle());

      double acceleration =
          (pidControllerActive.getSetpoint().velocity - lastSpeed)
              / (Timer.getFPGATimestamp() - lastTime);

      Logger.recordOutput("CoralPivot/Acceleration", acceleration);

      ffOutput =
          feedforwardActive.calculate(
              pidControllerActive.getSetpoint().position,
              pidControllerActive.getSetpoint().velocity,
              acceleration);

      lastVelocity = pidControllerActive.getSetpoint().velocity;
    } else {
      pidOutput = pidController.calculate(getAngle());

      double acceleration =
          (pidController.getSetpoint().velocity - lastVelocity)
              / (Timer.getFPGATimestamp() - lastTime);

      Logger.recordOutput("CoralPivot/Acceleration", acceleration);

      ffOutput =
          feedforward.calculate(
              pidController.getSetpoint().position,
              pidController.getSetpoint().velocity,
              acceleration);

      lastVelocity = pidController.getSetpoint().velocity;

      Logger.recordOutput("Shooter/PID output", pidOutput);
      Logger.recordOutput("Shooter/FF output", ffOutput);
    }

    setVoltage(MathUtil.clamp(pidOutput + ffOutput, -12, 12));

    lastTime = Timer.getFPGATimestamp();
  }

    @Override
    public void setP(double p) {
        pidController.setP(p);
    }

    @Override
    public void setI(double i) {
        pidController.setI(i);
    }

    @Override
    public void setD(double d) {
        pidController.setD(d);
    }

    @Override
    public double getP() {
        return pidController.getP();
    }

    @Override
    public double getI() {
        return pidController.getI();
    }

    @Override
    public double getD() {
        return pidController.getD();
    }
    @Override
    public void setActiveP(double p) {
        pidControllerActive.setP(p);
    }

    @Override
    public void setActiveI(double i) {
        pidControllerActive.setI(i);
    }

    @Override
    public void setActiveD(double d) {
        pidControllerActive.setD(d);
    }

    @Override
    public void setActivekS(double kS) {
        feedforwardActive.setKs(kS);
    }

    @Override
    public void setActivekG(double kG) {
        feedforwardActive.setKg(kG);
    }

    @Override
    public void setActivekV(double kV) {
        feedforwardActive.setKv(kV);
    }

    @Override
    public void setActivekA(double kA) {
        feedforwardActive.setKa(kA);
    }

    @Override
    public double getActivekS() {
        return feedforwardActive.getKs();
    }

    @Override
    public double getActivekG() {
        return feedforwardActive.getKg();
    }

    @Override
    public double getActivekV() {
        return feedforwardActive.getKv();
    }

    @Override
    public double getActivekA() {
        return feedforwardActive.getKa();
    }

    @Override
    public double getActiveP() {
        return pidControllerActive.getP();
    }

    @Override
    public double getActiveI() {
        return pidControllerActive.getI();
    }

    @Override
    public double getActiveD() {
        return pidControllerActive.getD();
    }
}
