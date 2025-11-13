package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ArmIOSim implements ArmIO {

  private final ProfiledPIDController m_controller;
  private final DCMotor m_armGearbox = DCMotor.getNEO(1);
  private SingleJointedArmSim sim =
      new SingleJointedArmSim(
          m_armGearbox,
          ArmConstants.kArmReduction,
          SingleJointedArmSim.estimateMOI(ArmConstants.kArmLength, ArmConstants.kArmMass),
          ArmConstants.kArmLength,
          ArmConstants.MIN_POSITION,
          ArmConstants.MAX_POSITION,
          true,
          0.1);

  public ArmIOSim() {
    m_controller =
        new ProfiledPIDController(
            ArmConstants.kPivotSimPID[0],
            ArmConstants.kPivotSimPID[1],
            ArmConstants.kPivotSimPID[2],
            new TrapezoidProfile.Constraints(2.45, 2.45));

    m_controller.setTolerance(0.1, 0.05);
  }

  @Override
  public void updateInputs(ArmIOInputs inputs) {
    sim.update(0.02);
    inputs.angleVelocityRadsPerSec = sim.getVelocityRadPerSec();
    inputs.angleVelocityRadsPerSec = sim.getAngleRads();
  }

  @Override
  public void setVoltage(double voltage) {
    sim.setInputVoltage(voltage);
  }

  @Override
  public void setPosition(double angleRads) {
    double pidOutput = m_controller.calculate(angleRads);
    sim.setInputVoltage(
        MathUtil.clamp(pidOutput, ArmConstants.MIN_POSITION, ArmConstants.MAX_POSITION));
  }

  public void stop() {
    setPosition(0);
  }
}
