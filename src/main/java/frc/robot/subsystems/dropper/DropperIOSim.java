package frc.robot.subsystems.dropper;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.ProfiledPIDController;

public class DropperIOSim implements DropperIO {
  private SparkMax leftMotor =
      new SparkMax(DropperConstants.LEFT_DROPPER_MOTOR_ID, MotorType.kBrushless);
  private RelativeEncoder leftEncoder = leftMotor.getEncoder();
  private final double pi = DropperConstants.PI;
  private ProfiledPIDController pidControllerSim =
      new ProfiledPIDController(0, 0, 0, DropperConstants.kDropperConstraints);

  @Override
  public void updateInputs(DropperIOInputs inputs) {
    sim.update(0.02); // updates
    inputs.appliedVoltage =
        leftMotor.getAppliedOutput() * leftMotor.getBusVoltage(); // from spark max
    inputs.angVelocityRadsPerSec = leftEncoder.getVelocity() * 2 * pi / 60;
    inputs.angleRads = leftEncoder.getPosition() * 2 * pi;
    inputs.angle = leftEncoder.getPosition() * 360;
    inputs.velocity = leftEncoder.getVelocity();
  }

  @Override
  public double getAngle() {
    return DropperIOInputs.angle;
  }

  @Override
  public double getAngVelocity() {
    return DropperIOInputs.velocity;
  }

  @Override
  public void setVoltage(double voltage) {}

  @Override
  public void setPosition(int position) {}

  @Override
  public void stop() {}

  // PID

  @Override
  public void setPIDGains(double kp, double ki, double kd) {}

  @Override
  public double getP() {
    return pidControllerSim.getP();
  }

  @Override
  public double getI() {
    return pidControllerSim.getI();
  }

  @Override
  public double getD() {
    return pidControllerSim.getD();
  }
}
