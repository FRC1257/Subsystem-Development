package frc.robot.subsystems.arm;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

import static frc.robot.subsystems.arm.ArmConstants.MAX_ACCELERATION;
import static frc.robot.subsystems.arm.ArmConstants.MAX_VELOCITY;

public class ArmIOSparkMax implements ArmIO{
    private ProfiledPIDController controller=new ProfiledPIDController(0,0,0,new TrapezoidProfile.Constraints(MAX_VELOCITY,MAX_ACCELERATION));

}
