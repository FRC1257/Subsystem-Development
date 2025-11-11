package frc.robot.subsystems.PersonalArm;

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

public class PersonalArmIOSparkMax implements PersonalArmIO {
    private SparkMax motor;

    private SparkMaxConfig config;
    private ProfiledPIDController controller;
 

    private ArmFeedforward feedforward;

    private SparkAbsoluteEncoder encoder;

    private double setPoint = 0.0;



    public PersonalArmIOSparkMax() {

    }

}
