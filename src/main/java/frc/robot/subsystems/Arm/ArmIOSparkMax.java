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
  //implement profilePIDController
  //feedfoward = extra credit
  
  private ProfiledPIDController controller =  new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(0, 0));
  
 
}
