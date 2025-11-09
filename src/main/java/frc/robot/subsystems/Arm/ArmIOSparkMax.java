package frc.robot.subsystems.Arm;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;

public class ArmIOSparkMax implements ArmIO {
    private SparkMax motor;

    private SparkMaxConfig config;
    private ProfiledPIDController controller;
    private ProfiledPIDController activeController;

    private ArmFeedforward feedforward;
    private ArmFeedforward activeFeedforward;

    private SparkAbsoluteEncoder encoder;
    private DigitalInput brakeBeam;
    
    private double setPoint = 0.0;

    private double kP = 1.8;
    private double kI = 0.0;
    private double kD = 2.3;

    private double kPa = 12.0;
    private double kIa = 3.4;
    private double kDa = 423.32;

    public ArmIOSparkMax() {
        motor = new SparkMax(1, SparkMax.MotorType.kBrushless);
        config = new SparkMaxConfig();
        encoder = motor.getAbsoluteEncoder();
        feedforward = new ArmFeedforward(0.0, 0.0, 0.0, 0.0);
        activeFeedforward = new ArmFeedforward(0.0, 0.0, 0.0, 0.0);

        config
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12.0)
        .smartCurrentLimit(3)
        .inverted(true);

        config
        .absoluteEncoder
        .zeroCentered(true)
        .zeroOffset(4.3)
        .positionConversionFactor(43.3)
        .velocityConversionFactor(54.3);

        controller = new ProfiledPIDController(kP, kI, kD, new TrapezoidProfile.Constraints(1,3));
        activeController = new ProfiledPIDController(kPa, kIa, kDa, new TrapezoidProfile.Constraints(3, 4));

        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        configureFF();
        configureFFActive();

    }

    private void configureFF() {
        setFFConstants(0.0, 0.0, 0.0, 0.0);
    }

    private void configureFFActive() {
        setActiveFFConstants(0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.angle = encoder.getPosition();
        inputs.velocity = encoder.getVelocity();
        inputs.appliedVoltage = motor.getAppliedOutput() * motor.getBusVoltage();
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    @Override
    public double getAngle() {
        return encoder.getPosition();
    }

    @Override
    public double getAngVelocity() {
        return encoder.getVelocity();
    }

    @Override

    public void setSetpoint(double setpoint) {
        controller.setGoal(setpoint);
        controller.reset(getAngle(), getAngVelocity());
        activeController.setGoal(setpoint);
        activeController.reset(getAngle(), getAngVelocity());
        //apply breakbeam into this
    }

    @Override
    public void setBrake(boolean brake) {
      SparkMaxConfig config = new SparkMaxConfig();
      config.idleMode(brake ? IdleMode.kBrake : IdleMode.kCoast);
      motor.configure(
          config, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }
    //needa figure what the is happening here
    //need to figure out gotosetpoint w/ breakbeam
    @Override
    public boolean atSetpoint() {
      return Math.abs(getAngle() - setPoint) < 8;
    }
  
    @Override
    public boolean isBreakBeamBroken() {
      // return breakBeam.get();
      return false;
    }

    @Override
    public void setP(double p) {
      controller.setP(p);
    }
  
    @Override
    public void setI(double i) {
      controller.setI(i);
    }
  
    @Override
    public void setD(double d) {
      controller.setD(d);
    }
  
    @Override
    public void setkS(double kS) {
      feedforward.setKs(kS);
    }
  
    @Override
    public void setkG(double kG) {
      feedforward.setKg(kG);
    }
  
    @Override
    public void setkV(double kV) {
      feedforward.setKv(kV);
    }
  
    @Override
    public void setkA(double kA) {
      feedforward.setKa(kA);
    }
  
    @Override
    public double getkS() {
      return feedforward.getKs();
    }
  
    @Override
    public double getkG() {
      return feedforward.getKg();
    }
  
    @Override
    public double getkV() {
      return feedforward.getKv();
    }
  
    @Override
    public double getkA() {
      return feedforward.getKa();
    }
  
    @Override
    public double getP() {
      return kP;
    }
  
    @Override
    public double getI() {
      return kI;
    }
  
    @Override
    public double getD() {
      return kD;
    }
  
    @Override
    public void setActiveP(double p) {
      activeController.setP(p);
    }
  
    @Override
    public void setActiveI(double i) {
        activeController.setI(i);
    }
  
    @Override
    public void setActiveD(double d) {
        activeController.setD(d);
    }
  
    @Override
    public void setActivekS(double kS) {
      activeFeedforward.setKs(kS);
    }
  
    @Override
    public void setActivekG(double kG) {
        activeFeedforward.setKg(kG);
    }
  
    @Override
    public void setActivekV(double kV) {
        activeFeedforward.setKv(kV);
    }
  
    @Override
    public void setActivekA(double kA) {
        activeFeedforward.setKa(kA);
    }
  
    @Override
    public double getActivekS() {
      return activeFeedforward.getKs();
    }
  
    @Override
    public double getActivekG() {
      return activeFeedforward.getKg();
    }
  
    @Override
    public double getActivekV() {
      return activeFeedforward.getKv();
    }
  
    @Override
    public double getActivekA() {
      return activeFeedforward.getKa();
    }
  
    @Override
    public double getActiveP() {
      return activeController.getP();
    }
  
    @Override
    public double getActiveI() {
      return activeController.getI();
    }
  
    @Override
    public double getActiveD() {
      return activeController.getD();
    }



  





}
