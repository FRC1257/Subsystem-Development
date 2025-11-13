package frc.robot.subsystems.Arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ArmIOSim implements ArmIO {
    private final DCMotor m_armGearbox = DCMotor.getNEO(1);
    private final ProfiledPIDController m_controller;

      private SingleJointedArmSim sim =
      new SingleJointedArmSim(
          m_armGearbox,
          CoralPivotConstants.CoralPivotSimConstants.kArmReduction,
          SingleJointedArmSim.estimateMOI(
              CoralPivotConstants.CoralPivotSimConstants.kArmLength,
              CoralPivotConstants.CoralPivotSimConstants.kArmMass),
          CoralPivotConstants.CoralPivotSimConstants.kArmLength,
          CoralPivotConstants.CoralPivotSimConstants.kMinAngleRads,
          CoralPivotConstants.CoralPivotSimConstants.kMaxAngleRads,
          true,
          0.1);
    public ArmIOSim() {
            m_controller =
        new ProfiledPIDController(
            CoralPivotConstants.CoralPivotSimConstants.kPivotSimPID[0],
            CoralPivotConstants.CoralPivotSimConstants.kPivotSimPID[1],
            CoralPivotConstants.CoralPivotSimConstants.kPivotSimPID[2],
            new TrapezoidProfile.Constraints(2.85, 15));

        
        m_controller.setTolerance(0.1, 0.05);
    }
    @Override
    public void updateInputs(ArmIOInputs inputs){
        sim.update(0.02);
        inputs.angVelocityRadsPerSec = sim.getVelocityRadPerSec();
        inputs.angleRads = sim.getAngleRads();
    }

    @Override
    public void setVoltage(double motorVolts){
        sim.setInputVoltage(motorVolts);
    }

    @Override
    public void setPosition(double position){
        double PIDOutput = m_controller.calculate(sim.getAngleRads(), position);
        sim.setInputVoltage(MathUtil.clamp(PIDOutput, -12, 12));
        
    }

    @Override
    public void stop() {
        sim.setInputVoltage(0);
    }


}
