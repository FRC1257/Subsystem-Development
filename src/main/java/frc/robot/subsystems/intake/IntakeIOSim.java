package frc.robot.subsystems.intake;

import static frc.robot.subsystems.intake.IntakeConstants.IntakeSimConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class IntakeIOSim implements IntakeIO {
  private final DCMotorSim motorSim;

  private static final double GEAR_RATIO = 3.0; // motor -> intake gears
  private static final double MOMENT_OF_INERTIA = 0.002; // kg*m^2

  private final FlywheelSim sim =
      new FlywheelSim(
          LinearSystemId.createFlywheelSystem(DCMotor.getNEO(1), kMomentOfInertia, kIntakeGearing),
          DCMotor.getNEO(1));
  private PIDController controller = new PIDController(0, 0, 0);

  private double appliedVoltage = 0.0;

  private boolean forward;

  public IntakeIOSim() {
    motorSim = new DCMotorSim(DCMotor.getNEO(1), GEAR_RATIO, MOMENT_OF_INERTIA);
  }

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    sim.update(0.02);
    inputs.velocityRadsPerSec = sim.getAngularVelocityRadPerSec();
    inputs.appliedVoltage = appliedVoltage;
    inputs.currentAmps = new double[] {sim.getCurrentDrawAmps()};
    inputs.tempCelcius = new double[] {60};
  }

  @Override
  public void setVoltage(double volts) {
    appliedVoltage = volts;
    sim.setInputVoltage(volts);
  }

  @Override
  public double getVoltage() {
    return appliedVoltage;
  }

  @Override
  public void setDirection(boolean forward) {
    this.forward = forward;
  }

  @Override
  public double getRPM() {
    return Units.radiansPerSecondToRotationsPerMinute(motorSim.getAngularVelocityRadPerSec());
  }

  @Override
  public void setRPM(double RPM) {
    double motorFreeRPM = 5676.0;
    double voltage = (RPM / motorFreeRPM);
  }
}
