package frc.robot.subsystems.intake;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import static frc.robot.subsystems.intake.IntakeConstants.IntakeSimConstants.*;

public class IntakeIOSim implements IntakeIO {
  private final FlywheelSim sim =
        new FlywheelSim(
            LinearSystemId.createFlywheelSystem(DCMotor.getNEO(1), kMomentOfInertia, kIntakeGearing),
          DCMotor.getNEO(1));
  private PIDController controller = new PIDController(0, 0, 0);

  private double appliedVoltage = 0.0;

  public IntakeIOSim() {}

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
}
